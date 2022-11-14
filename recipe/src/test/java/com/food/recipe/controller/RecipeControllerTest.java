package com.food.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.recipe.entity.IngredientEntity;
import com.food.recipe.model.IngredientDTO;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.service.RecipeService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.assertj.core.api.Assertions;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private RecipeService recipeService;

	@Test
	void getRecipes_Success() throws Exception {
		// public List<RecipeDTO> getRecipes();
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.getRecipes()).thenReturn(recipeDtoList);

		mockMvc.perform(get("/recipes")).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		});

	}
	
	@Test
	void getRecipes_NoContent() throws Exception {
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		when(recipeService.getRecipes()).thenReturn(recipeDtoList);

		mockMvc.perform(get("/recipes")).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
		});

	}
	
	@Test
	void getRecipes_Failure() throws Exception {
		when(recipeService.getRecipes()).thenThrow(InternalServerError.class);

   
      mockMvc.perform(get("/recipes")).andDo(result -> {
    	  Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		});
      
    

	}

	@Test
	void addRecipe_Failure() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		when(recipeService.addRecipe(ArgumentMatchers.any())).thenThrow(InternalServerError.class);

		mockMvc.perform(post("/add/recipe").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
					Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
				});

	}

	@Test
	void addRecipe_Success() throws Exception {
		// public List<RecipeDTO> getRecipes();
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeName("greek salad");
		recipeDto.setNumberOfServings(5);
		recipeDto.setVegetarian(false);
		recipeDto.setInstructions("put the ingrediants, put the pizza in oven");
		List<IngredientEntity> ingredientList = Arrays.asList(new IngredientEntity("corn" ),
				new IngredientEntity("onion"));
		recipeDto.setIngredients(ingredientList);
		when(recipeService.addRecipe(ArgumentMatchers.any())).thenReturn(recipeDto);

		mockMvc.perform(post("/add/recipe").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
					Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
				});

	}
//	@Test
//	void deleteRecipe_Failure()  {
//		
//		mockMvc.perform(delete("/delete/recipe").param("id", "1")).andDo(result -> {
//			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//		});
//	}
	
	@Test
	void deleteRecipe_Success() throws Exception {
		mockMvc.perform(delete("/delete/recipe").param("id", "1")).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		});
	}

	@Test
	void deleteAllRecipes_200() throws Exception {
		mockMvc.perform(delete("/delete/recipes")).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		});

	}

	@Test
	void updateRecipe_Success() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.updateRecipe(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(recipeDto);

		mockMvc.perform(put("/update/recipe").param("id", "1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
					Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
				});

	}
	
	@Test
	void updateRecipe_Failure() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.updateRecipe(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenThrow(InternalServerError.class);

		mockMvc.perform(put("/update/recipe").param("id", "1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
					Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
				});

	}
	
	@Test
	void updateRecipe_NotFound() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.updateRecipe(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(null);

		mockMvc.perform(put("/update/recipe").param("id", "1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
					Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
				});

	}

	@Test
	void getFilteredRecipes_Success() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.getSpecialRecipes(ArgumentMatchers.any())).thenReturn(recipeDtoList);

		mockMvc.perform(post("/search/recipes").contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		});

	}
	
	@Test
	void getFilteredRecipes_Failure() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeService.getSpecialRecipes(ArgumentMatchers.any())).thenThrow(InternalServerError.class);

		mockMvc.perform(post("/search/recipes").contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		});

	}
	
	@Test
	void getFilteredRecipes_NoContent() throws Exception {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
	

		when(recipeService.getSpecialRecipes(ArgumentMatchers.any())).thenReturn(recipeDtoList);

		mockMvc.perform(post("/search/recipes").contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsBytes(recipeDto))).andDo(result -> {
			Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
		});

	}
}
