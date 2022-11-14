package com.food.recipe.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.model.RecipeSearchDTO;

public interface RecipeService {

	public List<RecipeDTO> getRecipes();

	RecipeDTO addRecipe(RecipeDTO recipeDTO);

	void deleteRecipe(long id) throws Exception;

	void deleteAllRecipes();

	RecipeDTO updateRecipe(long id, RecipeDTO recipeDTO);
	
	List<RecipeDTO> getSpecialRecipes(RecipeSearchDTO recipeSearchDto);
}
