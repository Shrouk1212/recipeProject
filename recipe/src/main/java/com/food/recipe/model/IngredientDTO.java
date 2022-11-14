package com.food.recipe.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {

	private long ingredientId;
	private String ingredientName;
	public IngredientDTO(String ingredientName) {
		super();
		this.ingredientName = ingredientName;
	}
	
	
}
