package com.food.recipe.constants.enun;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Operator {
	
	
	EQUAL("equal");
	
	private String Operator;
	
    @Override
    public String toString() {
        return Operator;
    }

}

