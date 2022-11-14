package com.food.recipe.service;

import java.util.List;
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
