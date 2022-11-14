package com.food.recipe.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

	private String filterKey;
	private Object value;
	private String operator;
	private String fieldType;
	private List<Object> values;

}
