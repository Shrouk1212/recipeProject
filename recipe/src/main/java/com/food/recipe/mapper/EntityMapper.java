package com.food.recipe.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.food.recipe.entity.IngredientEntity;
import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.model.IngredientDTO;
import com.food.recipe.model.RecipeDTO;
@Component
public class EntityMapper {


	public List<RecipeDTO> toRecipeDTOList(List<RecipeEntity> recipeEntityList) {
		List<RecipeDTO> recipeDTOList = new ArrayList<>();
		if(recipeEntityList != null) {
		recipeEntityList.forEach(recipeEntity ->{
			recipeDTOList.add(recipeEntitytoDTO(recipeEntity));
			
		});
		}
		return recipeDTOList;
	}
	
	public RecipeEntity recipeDTOtoEntity(RecipeDTO recipeDTO) {
		RecipeEntity recipeEntity = new RecipeEntity();
		if(recipeDTO != null) {
		recipeEntity.setRecipeId(recipeDTO.getRecipeId());
		recipeEntity.setRecipeName(recipeDTO.getRecipeName());
		recipeEntity.setNumberOfServings(recipeDTO.getNumberOfServings());
		recipeEntity.setInstructions(recipeDTO.getInstructions());
		recipeEntity.setVegetarian(recipeDTO.isVegetarian());
		List<IngredientEntity> ingredientEntityList = new ArrayList<>();
		if (recipeDTO.getIngredients() != null) {
		recipeDTO.getIngredients().forEach(ingredientDTO ->{
			ingredientEntityList.add(ingredientDTOtoEntity(ingredientDTO));
		});
		}
		recipeEntity.setIngredients(ingredientEntityList);
		}
		return recipeEntity;
		
	}
	
	public IngredientEntity ingredientDTOtoEntity(IngredientDTO ingredientDTO) {
		IngredientEntity ingredientEntity = new IngredientEntity();
		ingredientEntity.setIngredientId(ingredientDTO.getIngredientId());
		ingredientEntity.setIngredientName(ingredientDTO.getIngredientName());
		return ingredientEntity;
		
	}
	
	public RecipeDTO recipeEntitytoDTO(RecipeEntity recipeEntity) {
		RecipeDTO recipeDTO = new RecipeDTO();
		if(recipeEntity != null) {
		recipeDTO.setRecipeId(recipeEntity.getRecipeId());
		recipeDTO.setRecipeName(recipeEntity.getRecipeName());
		recipeDTO.setNumberOfServings(recipeEntity.getNumberOfServings());
		recipeDTO.setInstructions(recipeEntity.getInstructions());
		recipeDTO.setVegetarian(recipeEntity.isVegetarian());
		Set<IngredientDTO> ingredientDTOList = new HashSet<>();
		if(recipeEntity.getIngredients() != null) {
			recipeEntity.getIngredients().forEach(ingredientEntity ->{
				ingredientDTOList.add(ingredientEntitytoDTO(ingredientEntity));
			});
		}
		
		recipeDTO.setIngredients(ingredientDTOList);
		}
		return recipeDTO;
		
	}
	
	public IngredientDTO ingredientEntitytoDTO(IngredientEntity ingredientEntity) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setIngredientId(ingredientEntity.getIngredientId());
		ingredientDTO.setIngredientName(ingredientEntity.getIngredientName());
		return ingredientDTO;
		
	}

}
