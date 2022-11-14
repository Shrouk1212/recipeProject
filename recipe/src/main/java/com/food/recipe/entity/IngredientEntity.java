package com.food.recipe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class IngredientEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long ingredientId;
	

	String ingredientName;
	
	@ManyToMany(mappedBy = "ingredients")
	@JsonIgnore
	List<RecipeEntity> recipes;

	public IngredientEntity( String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public IngredientEntity(String ingredientName, List<RecipeEntity> recipes) {
		super();
		this.ingredientName = ingredientName;
		this.recipes = recipes;
	}
	
	
	
	


}
