package com.food.recipe.service.imp;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.food.recipe.entity.RecipeEntity;
import com.food.recipe.mapper.EntityMapper;
import com.food.recipe.model.RecipeDTO;
import com.food.recipe.model.RecipeSearchDTO;
import com.food.recipe.repository.IngredientRepository;
import com.food.recipe.repository.RecipeRepository;
import com.food.recipe.service.RecipeService;
import com.food.recipe.specification.RecipeSpecifications;

@Service
public class RecipeServiceImp implements RecipeService {

	RecipeRepository recipeRepository;

	IngredientRepository ingredientRepository;
	EntityMapper entityMapper;
	
	RecipeSpecifications recipeSpecifications;

	public RecipeServiceImp(RecipeRepository recipeRepository, EntityMapper entityMapper,
			IngredientRepository ingredientRepository , RecipeSpecifications recipeSpecifications) {
		this.recipeRepository = recipeRepository;
		this.entityMapper = entityMapper;
		this.ingredientRepository = ingredientRepository;
		this.recipeSpecifications = recipeSpecifications;
	}

	public List<RecipeDTO> getRecipes() {
		return entityMapper.toRecipeDTOList(recipeRepository.findAll());
	}

	public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
		return entityMapper.recipeEntitytoDTO(recipeRepository.saveAndFlush(entityMapper.recipeDTOtoEntity(recipeDTO)));
	}

	public void deleteRecipe(long id) {
		recipeRepository.deleteById(id);
	}

	public void deleteAllRecipes() {
		recipeRepository.deleteAll();
	}

	public RecipeDTO updateRecipe(long id, RecipeDTO recipeDTO) {
		Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(id);
		RecipeDTO updatedrecipeDTO = null;
		if (recipeEntityOptional.isPresent()) {
			RecipeEntity recipeEntity = recipeEntityOptional.get();
			recipeEntity = entityMapper.recipeDTOtoEntity(recipeDTO);
			updatedrecipeDTO = entityMapper.recipeEntitytoDTO(recipeRepository.save(recipeEntity));
		}
		return updatedrecipeDTO;
	}

	public List<RecipeDTO> getSpecialRecipes(RecipeSearchDTO recipeSearchDto) {
		return entityMapper
				.toRecipeDTOList(recipeRepository.findAll(recipeSpecifications.getRecipeSpecifications(recipeSearchDto)));
	}

}
