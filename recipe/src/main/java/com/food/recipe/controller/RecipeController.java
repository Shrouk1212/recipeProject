package com.food.recipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.model.RecipeSearchDTO;
import com.food.recipe.service.RecipeService;

@RestController
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	/**
	 *  Returns all the recipes
	 * 
	 * @return The list of recipes
	*/
	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeDTO>> getRecipes() {
		try {
			List<RecipeDTO> recipes = recipeService.getRecipes();
			if (recipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseEntity.status(HttpStatus.OK).body(recipes);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *  Inserts a new recipe
	 * 
	 * @param recipeDTO The recipe data transfer object
	 * 
	 * @return the added recipe
	*/
	@PostMapping("add/recipe")
	public ResponseEntity<RecipeDTO> addRecipe(@RequestBody RecipeDTO recipeDTO) {
		RecipeDTO createdRecipeDTO = null;
		try {
			createdRecipeDTO = recipeService.addRecipe(recipeDTO);
			return new ResponseEntity<>(createdRecipeDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *  delete a recipe
	 * 
	 * @param id the id of the recipe that will be deleted
	 * 
	*/
	@DeleteMapping("delete/recipe")
	public ResponseEntity<HttpStatus> deleteRecipe(@RequestParam long id) {
		try {
			recipeService.deleteRecipe(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *  delete All recipes
	*/
	@DeleteMapping("delete/recipes")
	public ResponseEntity<HttpStatus> deleteAllRecipes() {
		try {
			recipeService.deleteAllRecipes();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 *  update a specific recipe
	 * 
	 * @param1 id 		 the id of the recipe that will be deleted
	 * @param2 recipeDTO the new recipe DTO
	 * 
	 * @return the updated recipe DTO
	 * 
	*/
	@PutMapping("update/recipe")
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestParam long id, @RequestBody RecipeDTO recipeDTO) {
		try {
			RecipeDTO updatedRecipeDTO = recipeService.updateRecipe(id, recipeDTO);
			if (updatedRecipeDTO != null) {
				return new ResponseEntity<>(updatedRecipeDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Filters and returns the recipes based on the params provided
	 * 
	 * if the user wants to filter on specific recipe, the user may provide list of rules 
	 * if the user wants to include some ingredients the filter key should be ingredients, the operator should be equal and provide a list of ingredients in values array
	 * if the user wants to exclude some ingredients the filter key should be ingredients, the operator should be not-equal and provide list of ingredient in values array
	 * if the user wants to search for a word the key should be instructions and the operator should be equal and provide the word in value and provide the type of the value which is string
	 * 
	 * @param recipeSearchDto the roles of filtering
	 * 
	 * @return the filtered recipes
	 * 
	*/
	@PostMapping("search/recipes")
	public ResponseEntity<List<RecipeDTO>> getfilteredRecipes(@RequestBody RecipeSearchDTO recipeSearchDto) {
		try {
			List<RecipeDTO> recipes = recipeService.getSpecialRecipes(recipeSearchDto);
			if (recipes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseEntity.status(HttpStatus.OK).body(recipes);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}