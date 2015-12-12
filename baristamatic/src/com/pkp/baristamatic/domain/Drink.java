package com.pkp.baristamatic.domain;

import java.util.ArrayList;
import java.util.List;

import com.pkp.baristamatic.domain.ingredient.Ingredient;

/**
 * Represnts a drink
 * @author pravat
 */
public class Drink {

	private List<DrinkIngredient> allIngredients;
	private DrinkType type;
	
	/**
	 * Creates a drink instance with only the type specified. the {@link #addIngredient(Ingredient, int)}
	 * method must be called for adding ingredients to this drik
	 * @param productType
	 */
	public Drink(DrinkType productType) {
		type = productType;
	}

	/**
	 * Calculates the price of the drink. The drink price is calculated by 
	 * the sum of prices of its ingredients
	 * @return the price
	 */
	public float getPrice() {
		float productPrice = 0.0F;
		if(null != allIngredients) {
			for (DrinkIngredient ingredientSize : allIngredients) {
				productPrice += (ingredientSize.getUnits() * ingredientSize.getIngredient().getPrice());
			}
		}
		return productPrice;
	}

	/**
	 * Adds the given number of units of the Ingredient to the drink
	 * @param ing
	 * @param units
	 */
	public void addIngredient(Ingredient ing, int units) {
		if(allIngredients == null) allIngredients = new ArrayList<DrinkIngredient>();
		DrinkIngredient ingredientSize = new DrinkIngredient(ing, units);
		allIngredients.add(ingredientSize);
	}
	
	public DrinkType getType() {
		return type;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}
	
	public List<DrinkIngredient> getIngredients() {
		return allIngredients;
	}
}
