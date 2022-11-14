package com.food.recipe.model;

import java.util.List;
import com.food.recipe.entity.IngredientEntity;
import lombok.AllArgsConstructor;
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
	private List<IngredientEntity> ingredients;
	
	public RecipeDTO( String recipeName, String instructions, int numberOfServings, boolean vegetarian,
			List<IngredientEntity> ingredients) {
		super();
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.numberOfServings = numberOfServings;
		this.vegetarian = vegetarian;
		this.ingredients = ingredients;
	}
	
	

	
	

}
