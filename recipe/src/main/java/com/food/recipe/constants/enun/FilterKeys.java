package com.food.recipe.constants.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FilterKeys {
	
	
	VEGETARIAN("vegetarian"),
	INSTRUCTIONS("instructions"),
	INGREDIENTS("ingredients");
	private String filterKey;
	
    @Override
    public String toString() {
        return filterKey;
    }

}
