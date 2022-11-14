package com.food.recipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.food.recipe.entity.IngredientEntity;
import com.food.recipe.entity.RecipeEntity;


public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> , JpaSpecificationExecutor<IngredientEntity>{

	
	Optional<IngredientEntity>  findByIngredientName(String name);
}
