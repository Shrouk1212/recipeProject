package com.food.recipe.service.imp;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.mapper.EntityMapper;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.model.RecipeSearchDTO;
import com.food.recipe.repository.RecipeRepository;
import com.food.recipe.service.RecipeService;
import com.food.recipe.specification.RecipeSpecifications;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RecipeServiceImpTest {

	@MockBean
	RecipeRepository recipeRepository;
	@MockBean
	EntityMapper entityMapper;
	@Autowired
	RecipeService recipeServiceImp;
	@MockBean
	RecipeSpecifications recipeSpecifications;
	RecipeServiceImpTest(){
		
	}

 
	@Test
	void whenGetRecipes_Success() {
		
		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setRecipeId(1L);
		List<RecipeEntity> recipeEntityList = new ArrayList<RecipeEntity>();
		recipeEntityList.add(recipeEntity);
		
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);

		when(recipeRepository.findAll()).thenReturn(recipeEntityList);
		when(entityMapper.toRecipeDTOList(recipeRepository.findAll())).thenReturn(recipeDtoList);
		
		List<RecipeDTO> recipesList = recipeServiceImp.getRecipes();
		 assertTrue(recipesList.size() > 0);
	}
	
	
	@ParameterizedTest
	@MethodSource("generatRecipeDTOData")
	void addRecipe_Success(RecipeDTO recipeDTO) {
		RecipeEntity recipeEntity = new RecipeEntity();
		RecipeDTO updatedrecipeDto = new RecipeDTO();
		updatedrecipeDto.setRecipeName("Pasta");
		when(entityMapper.recipeDTOtoEntity(ArgumentMatchers.any())).thenReturn(recipeEntity);
		when(recipeRepository.saveAndFlush(ArgumentMatchers.any())).thenReturn(recipeEntity);
		when(entityMapper.recipeEntitytoDTO(ArgumentMatchers.any())).thenReturn(updatedrecipeDto);

		 updatedrecipeDto = recipeServiceImp.addRecipe(recipeDTO);
		 Assertions.assertThat(updatedrecipeDto.getRecipeName()).isEqualTo(recipeDTO.getRecipeName());

	}
	
	private static Stream<Arguments> generatRecipeDTOData() {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setRecipeName("Pasta");
	    return Stream.of(
	        Arguments.of(recipeDTO)
	    );
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {1})
	void deleteRecipe_200(int id) throws Exception {
		recipeServiceImp.deleteRecipe(1);
		verify(recipeRepository, times(1)).deleteById(ArgumentMatchers.anyLong());
		
	}

	@Test
	void deleteAllRecipes_200() throws Exception {
		recipeServiceImp.deleteAllRecipes();
		verify(recipeRepository, times(1)).deleteAll();
	}

	@ParameterizedTest
	@MethodSource("generatUpdateData")
	void updateRecipe_200(long id, RecipeDTO recipeDTO) throws Exception {
		// public List<RecipeDTO> getRecipes();
		RecipeDTO updatedrecipeDto = new RecipeDTO();
		RecipeEntity recipeEntity = new RecipeEntity();
		recipeEntity.setRecipeName("Pasta");
		updatedrecipeDto.setRecipeName("Pasta");
		Optional<RecipeEntity> recipeEntityOptional =Optional.of(recipeEntity);

		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeEntityOptional);
		when(entityMapper.recipeDTOtoEntity(ArgumentMatchers.any())).thenReturn(recipeEntity);
		when(recipeRepository.save(ArgumentMatchers.any())).thenReturn(recipeEntity);
		when(entityMapper.recipeEntitytoDTO(ArgumentMatchers.any())).thenReturn(updatedrecipeDto);
		
		recipeServiceImp.updateRecipe(id, recipeDTO);
		
		Assertions.assertThat(updatedrecipeDto.getRecipeName()).isEqualTo(recipeDTO.getRecipeName());
		
	}
	private static Stream<Arguments> generatUpdateData() {
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.setRecipeName("Pasta");
	    return Stream.of(
	        Arguments.of(1 , recipeDTO)
	    );
	}
	
	
	@ParameterizedTest
	@MethodSource("generatRecipeSearchDTO")
	void getFilteredRecipes_200(RecipeSearchDTO recipeSearchDto) throws Exception {
		// public List<RecipeDTO> getRecipes();
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setRecipeId(1L);
		List<RecipeEntity> recipeEntityList = new ArrayList<RecipeEntity>();
		//recipeDtoList.add(recipeDto);
		Specification<RecipeEntity> specificationEntity = Specification.where(null) ;
		List<RecipeDTO> recipeDtoList = new ArrayList<RecipeDTO>();
		recipeDtoList.add(recipeDto);
		when(recipeSpecifications.getRecipeSpecifications(ArgumentMatchers.any())).thenReturn(specificationEntity);

		when(recipeRepository.findAll(recipeSpecifications.getRecipeSpecifications(recipeSearchDto))).thenReturn(recipeEntityList);
		
		when(entityMapper.toRecipeDTOList(recipeRepository.findAll())).thenReturn(recipeDtoList);
		
		recipeServiceImp.getSpecialRecipes(recipeSearchDto);
		verify(recipeRepository, times(1)).findAll(recipeSpecifications.getRecipeSpecifications(recipeSearchDto));

	}
	
	private static Stream<Arguments> generatRecipeSearchDTO() {
		RecipeSearchDTO recipeSearchDto = new RecipeSearchDTO();
	    return Stream.of(
	        Arguments.of(recipeSearchDto)
	    );
	}

}
