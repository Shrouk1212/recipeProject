package com.food.recipe.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.food.recipe.entity.RecipeEntity;


public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> ,JpaSpecificationExecutor<RecipeEntity> {


	Optional<RecipeEntity>  findByRecipeName(String name);
	
	@Modifying
	@Transactional
	@Query(value = "insert into recipe_ingredient (recipe_id , ingredient_id) VALUES (:recipeId , :ingredientId)", nativeQuery = true)
	int insertRecipeIngredientIds(@Param("recipeId") Long recipeId, @Param("ingredientId") Long ingredientId);

}
