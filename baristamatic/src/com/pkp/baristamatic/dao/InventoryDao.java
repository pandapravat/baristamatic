package com.pkp.baristamatic.dao;

import com.pkp.baristamatic.domain.ingredient.IngredientType;
import com.pkp.baristamatic.utl.InventoryPrintVisitor;
import com.pkp.baristamatic.utl.Visitee;

public interface InventoryDao extends Visitee<InventoryPrintVisitor> {
	/**
	 * Reduce the inventory with specified units of ingredients
	 * 
	 * @param ingredientType the ingredient type
	 * @param units the units
	 */
	void reduce(IngredientType ingredientType, int units);
	
	/**
	 * Checks if an ingredient is available in the inventory
	 * @param ingredient the ingredient
	 * @param units the number of units to check for availabilty
	 * @return
	 */
	boolean isAvailable(IngredientType ingredient, int units);
	
	/**
	 * Reloads the inventory items with a fixed number of units
	 */
	public void reload();
}
