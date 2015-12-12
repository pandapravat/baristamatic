package com.pkp.baristamatic.utl;

import java.util.Map;

import com.pkp.baristamatic.domain.IngredientStore;
import com.pkp.baristamatic.domain.ingredient.IngredientType;

/**
 * Visitor class responsible for printing the contents of the inventory
 * @author pravat
 *
 */
public class InventoryPrintVisitor implements Visitor<IngredientStore>{

	private static String DELIM = ",";
	/**
	 * Prints the available stocks in the inventory. This method prints the
	 * stocks/ingredients in a alphabetical order 
	 */
	@Override
	public void visit(IngredientStore inventory) {
		System.out.println("Inventory:");
		Map<IngredientType, Integer> ingredientMap = inventory.getIngredientAvailabilityMap();
		for (Map.Entry<IngredientType, Integer> anEntry : ingredientMap.entrySet()) {
			System.out.println(anEntry.getKey().getName() + DELIM + anEntry.getValue());
		}
	}
	
}
