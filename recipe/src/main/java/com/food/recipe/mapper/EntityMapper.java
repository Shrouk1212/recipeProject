package com.food.recipe.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.model.RecipeDTO;
@Component
public class EntityMapper {


	public List<RecipeDTO> toRecipeDTOList(List<RecipeEntity> recipeEntityList) {
		List<RecipeDTO> recipeDTOList = new ArrayList<>();
		recipeEntityList.forEach(recipeEntity ->{
			recipeDTOList.add(recipeEntitytoDTO(recipeEntity));
			
		});
		
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
		recipeEntity.setIngredients(recipeDTO.getIngredients());
		}
		return recipeEntity;
		
	}
	
	public RecipeDTO recipeEntitytoDTO(RecipeEntity recipeEntity) {
		RecipeDTO recipeDTO = new RecipeDTO();
		if(recipeEntity != null) {
		recipeDTO.setRecipeId(recipeEntity.getRecipeId());
		recipeDTO.setRecipeName(recipeEntity.getRecipeName());
		recipeDTO.setNumberOfServings(recipeEntity.getNumberOfServings());
		recipeDTO.setInstructions(recipeEntity.getInstructions());
		recipeDTO.setVegetarian(recipeEntity.isVegetarian());
		recipeDTO.setIngredients(recipeEntity.getIngredients());
		}
		return recipeDTO;
		
	}

}
