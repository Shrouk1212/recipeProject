package com.food.recipe.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipe")
public class RecipeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recipeId;
	private String recipeName;
	private String instructions;
	private Integer numberOfServings;
	private boolean vegetarian;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
	  name = "recipe_ingredient", 
	  joinColumns = @JoinColumn(name = "recipe_id"), 
	  inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<IngredientEntity> ingredients;

	

}
