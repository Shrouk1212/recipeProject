package com.food.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.food.recipe.entity.IngredientEntity;


public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> , JpaSpecificationExecutor<IngredientEntity>{

}
