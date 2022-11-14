package com.food.recipe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.food.recipe.entity.RecipeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {

	private long ingredientId;
	private String ingredientName;
	@JsonIgnore
	private List<RecipeEntity> recipes;
}
