package com.food.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.food.recipe.entity.RecipeEntity;


public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> ,JpaSpecificationExecutor<RecipeEntity> {

}
