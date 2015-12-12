package com.pkp.baristamatic.domain;

import com.pkp.baristamatic.domain.ingredient.Ingredient;

/**
 * Represent a ingredient-unit combination
 * @author pravat
 */
public class DrinkIngredient {
	// the ingredient
	private Ingredient ingredient;
	// the units of the ingredient
	private int units;
	
	public DrinkIngredient(Ingredient ingredient, int units) {
		super();
		this.ingredient = ingredient;
		this.units = units;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
}
