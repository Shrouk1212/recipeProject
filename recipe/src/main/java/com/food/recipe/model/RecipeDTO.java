package com.food.recipe.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.food.recipe.entity.IngredientEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {

	private Long recipeId;
	private String recipeName;
	private String instructions;
	private int numberOfServings;
	private boolean vegetarian;
	private Set<IngredientDTO> ingredients = new HashSet<>();
	
	public RecipeDTO( String recipeName, String instructions, int numberOfServings, boolean vegetarian,
			Set<IngredientDTO> ingredients) {
		super();
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.numberOfServings = numberOfServings;
		this.vegetarian = vegetarian;
		this.ingredients = ingredients;
	}
	
	

	
	

}
