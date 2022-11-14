package com.food.recipe.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.food.recipe.RecipeApplication;

import org.springframework.beans.factory.annotation.Value;

@SpringBootTest(classes = RecipeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Value("${local-server.url}")
	String url;

	@Test
	public void getRecipes_returnOK() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<>(null, null);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/recipes"), HttpMethod.GET, entity,
				String.class);

		String expected = "[{\"recipeId\":1,\"recipeName\":\"masala pasta\",\"instructions\":\"boil the macaroni, put the tomatos, put the macaroni in the oven\",\"numberOfServings\":4,\"vegetarian\":true,\"ingredients\":[{\"ingredientId\":1,\"ingredientName\":\"potatoes\"}]},{\"recipeId\":3,\"recipeName\":\"tuna pasta\",\"instructions\":\"boil the macaroni, put the tuna\",\"numberOfServings\":1,\"vegetarian\":false,\"ingredients\":[{\"ingredientId\":2,\"ingredientName\":\"macaroni\"},{\"ingredientId\":3,\"ingredientName\":\"tuna\"}]},{\"recipeId\":4,\"recipeName\":\"lassania\",\"instructions\":\"boil the macaroni, put bashamel, put in oven\",\"numberOfServings\":1,\"vegetarian\":true,\"ingredients\":[{\"ingredientId\":8,\"ingredientName\":\"corn\"},{\"ingredientId\":9,\"ingredientName\":\"onion\"}]}]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addRecipe() throws JSONException {

		String body = "{\"recipeName\":\"salad\",\"instructions\":\"put ingredients together\",\"numberOfServings\":1,\"vegetarian\":true,\"ingredients\":[{\"ingredientName\":\"corn\"},{\"ingredientName\":\"onion\"}]}";

		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/add/recipe"), HttpMethod.POST,
				entity, String.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void updateRecipe() throws JSONException {

		String body = "{\"recipeId\":3,\"recipeName\":\"tuna pasta\",\"instructions\":\"boil the macaroni, put the tuna\",\"numberOfServings\":1,\"vegetarian\":false,\"ingredients\":[{\"ingredientId\":2,\"ingredientName\":\"macaroni\"},{\"ingredientId\":3,\"ingredientName\":\"tuna\"}]}";
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/update/recipe?id=3"),
				HttpMethod.PUT, entity, String.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void deleteByIdRecipe() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, null);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/delete/recipe?id=6"),
				HttpMethod.DELETE, entity, String.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void deleteAllRecipes() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, null);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/delete/recipes"), HttpMethod.DELETE,
				entity, String.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void felteringRecipes() throws JSONException {

String body = "{\"searchCriteriaList\":[{\"filterKey\":\"ingredients\",\"operator\":\"equal\",\"fieldType\":\"array\",\"value\":\"\",\"values\":[\"tuna\"]}]}";
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(("/search/recipes")), HttpMethod.POST,
				entity, String.class);
		
		
		String expected = "[{\"recipeId\":3,\"recipeName\":\"tuna pasta\",\"instructions\":\"boil the macaroni, put the tuna\",\"numberOfServings\":1,\"vegetarian\":false,\"ingredients\":[{\"ingredientId\":2,\"ingredientName\":\"macaroni\"},{\"ingredientId\":3,\"ingredientName\":\"tuna\"}]}]";

		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return url +":" + port + uri;
	}

}
