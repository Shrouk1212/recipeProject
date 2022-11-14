package com.food.recipe.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.model.RecipeDTO;

@SpringBootTest
public class MapperTest {

	@Autowired
	EntityMapper mapper;
	
	public MapperTest() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ParameterizedTest
	@MethodSource("generatRecipeEntityList")
	void toRecipeDTOList(List<RecipeEntity> recipeEntityList) {
		RecipeEntity recipeEntity = recipeEntityList.get(0);
		RecipeDTO recipeDTO = mapper.toRecipeDTOList(recipeEntityList).get(0);
		Assertions.assertThat(recipeDTO.getRecipeName()).isEqualTo(recipeEntity.getRecipeName());
		

	}
	
	private static Stream<Arguments> generatRecipeEntityList() {
		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setRecipeName("Pasta");
		recipeEntity.setInstructions("put pasta and tomato, boil them together");
		recipeEntity.setNumberOfServings(1);
		
		
		List<RecipeEntity> recipeEntityList = new ArrayList<>();
		recipeEntityList.add(recipeEntity);
	    return Stream.of(
	        Arguments.of(recipeEntityList)
	    );
	}
	
	
	@ParameterizedTest
	@MethodSource("generatRecipeDTO")
	void  recipeDTOtoEntity(RecipeDTO recipeDTO) {
		RecipeEntity recipeEntity = mapper.recipeDTOtoEntity(recipeDTO);	
		Assertions.assertThat(recipeEntity.getRecipeName()).isEqualTo(recipeDTO.getRecipeName());
		

		
	}
	
	private static Stream<Arguments> generatRecipeDTO() {
		RecipeDTO recipeDTO = new RecipeDTO();
		
		recipeDTO.setRecipeName("Pasta");
		recipeDTO.setInstructions("put pasta and tomato, boil them together");
		recipeDTO.setNumberOfServings(1);
	    return Stream.of(
	        Arguments.of(recipeDTO)
	    );
	}

	
	@ParameterizedTest
	@MethodSource("generatRecipeEntity")
	public void recipeEntitytoDTO(RecipeEntity recipeEntity) {
		RecipeDTO recipeDTO = mapper.recipeEntitytoDTO(recipeEntity);	
		Assertions.assertThat(recipeEntity.getRecipeName()).isEqualTo(recipeDTO.getRecipeName());
	}
	
	private static Stream<Arguments> generatRecipeEntity() {
		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setRecipeName("Pasta");
		recipeEntity.setInstructions("put pasta and tomato, boil them together");
		recipeEntity.setNumberOfServings(1);
	    return Stream.of(
	        Arguments.of(recipeEntity)
	    );
	}



}
