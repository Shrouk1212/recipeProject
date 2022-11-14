package com.food.recipe.specification;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.food.recipe.constants.enun.FilterKeys;
import com.food.recipe.constants.enun.Operator;
import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.entity.IngredientEntity;
import com.food.recipe.model.RecipeSearchDTO;
import com.food.recipe.model.SearchCriteria;

@Component
public class RecipeSpecifications {

final String  INGREDIENT_NAME= "ingredientName";
final String RECIPE_ID ="recipeId";

	public  Specification<RecipeEntity> getRecipeSpecifications(RecipeSearchDTO recipeSearchDto) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			for (SearchCriteria searchCriteria : recipeSearchDto.getSearchCriteriaList()) {

				if (searchCriteria.getFilterKey().toLowerCase().equals(FilterKeys.VEGETARIAN.toString())) {
					if (searchCriteria.getValue().equals(true)) {
						predicates.add(criteriaBuilder.isTrue(root.get(searchCriteria.getFilterKey())));
					} else if (searchCriteria.getValue().equals(false)) {
						predicates.add(criteriaBuilder.isFalse(root.get(searchCriteria.getFilterKey())));
					}
				} else if (searchCriteria.getFilterKey().toLowerCase().equals(FilterKeys.INGREDIENTS.toString())) {
					List<Object> values = searchCriteria.getValues();
				
					if(searchCriteria.getOperator().toLowerCase().equals(Operator.EQUAL.toString())) {
						for (Object value : values) {
							predicates.add(criteriaBuilder.equal(root.join(FilterKeys.INGREDIENTS.toString()).get(INGREDIENT_NAME), value));
						}
					}else {
					    CriteriaQuery<RecipeEntity> cq = criteriaBuilder.createQuery(RecipeEntity.class);
					  
						for (Object value : values) {
							 query.distinct(true);
							 Subquery<Long> subquery = cq.subquery(Long.class);
							 
						        Root<RecipeEntity> subqueryStudent = subquery.from(RecipeEntity.class);
						        
						        Join<RecipeEntity, IngredientEntity> subqueryCourse = subqueryStudent.join(FilterKeys.INGREDIENTS.toString());
						        
							 subquery.select(subqueryStudent.get(RECIPE_ID)).where(
									 criteriaBuilder.equal(subqueryCourse.get(INGREDIENT_NAME), value));
							 
							 predicates.add(criteriaBuilder.in(root.get(RECIPE_ID)).value(subquery).not());

						}
					}
					

				} else if (searchCriteria.getFilterKey().toLowerCase().equals(FilterKeys.INSTRUCTIONS.name())) {
					predicates.add(criteriaBuilder.like(root.get(searchCriteria.getFilterKey()),
							"%" + searchCriteria.getValue() + "%"));
				} else {

					predicates.add(
							criteriaBuilder.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue()));
				}

			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}
