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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.model.RecipeSearchDTO;
import com.food.recipe.model.SearchCriteria;
import com.food.recipe.service.RecipeService;

@RestController
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	// select all
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

	// insert
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

	// delete by id
	@DeleteMapping("delete/recipe")
	public ResponseEntity<HttpStatus> deleteRecipe(@RequestParam long id) {
		try {
			recipeService.deleteRecipe(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// deleteAll
	@DeleteMapping("delete/recipes")
	public ResponseEntity<HttpStatus> deleteAllRecipes() {
		try {
			recipeService.deleteAllRecipes();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update
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
