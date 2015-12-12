package com.pkp.baristamatic.builder;

import com.pkp.baristamatic.domain.ingredient.Cocoa;
import com.pkp.baristamatic.domain.ingredient.Coffee;
import com.pkp.baristamatic.domain.ingredient.Cream;
import com.pkp.baristamatic.domain.ingredient.DecafCoffee;
import com.pkp.baristamatic.domain.ingredient.Espresso;
import com.pkp.baristamatic.domain.ingredient.FoamedMilk;
import com.pkp.baristamatic.domain.ingredient.Ingredient;
import com.pkp.baristamatic.domain.ingredient.IngredientType;
import com.pkp.baristamatic.domain.ingredient.SteamedMilk;
import com.pkp.baristamatic.domain.ingredient.Sugar;
import com.pkp.baristamatic.domain.ingredient.WhippedCream;

/**
 * Builds an ingredient
 * @author pravat
 *
 */
public class IngredientBuilder implements Builder<IngredientType, Ingredient>{

	private static IngredientBuilder builder;
	
	/**
	 * Builds an ingredient given its type
	 */
	public Ingredient build(IngredientType type) {
		Ingredient ingredient;
		switch (type) {
		case COFFEE:
			ingredient = new Coffee();
			break;
		case COCOA:
			ingredient = new Cocoa();
			break;
		case CREAM:
			ingredient = new Cream();
			break;
		case DECAF_COFFEE:
			ingredient = new DecafCoffee();
			break;
		case ESPRESSO:
			ingredient = new Espresso();
			break;
		case FOAMED_MILK:
			ingredient = new FoamedMilk();
			break;
		case STEAMED_MILK:
			ingredient = new SteamedMilk();
			break;
		case SUGAR:
			ingredient = new Sugar();
			break;
		case WHIPPED_CREAM:
			ingredient = new WhippedCream();
			break;
		default:
			throw new RuntimeException("Type not defined");
		}

		return ingredient;
	}
	private IngredientBuilder() {
		// marked private to allow singleton instances
	}
	/**
	 * Returns a singleton instance of this inventory
	 * @return the instance if already available else a new one
	 */
	public static IngredientBuilder getInstance() {
		if(null == builder) {
			synchronized (IngredientBuilder.class) {
				if(null == builder) {
					builder = new IngredientBuilder();
				}
			}
		}
		return builder;
	}
}
