package com.pkp.baristamatic.dao;

import java.util.Map;

import com.pkp.baristamatic.domain.ConcurrentInventoryUpdateException;
import com.pkp.baristamatic.domain.IngredientStore;
import com.pkp.baristamatic.domain.ingredient.IngredientType;
import com.pkp.baristamatic.utl.InventoryPrintVisitor;

/**
 * Represents an inventory or a stock of the available ingredients. 
 * 
 * @author pravat
 */
public class InventoryDaoImpl implements InventoryDao {
	//private Map<IngredientType, Integer> ingredientAvailabilityMap;
	IngredientStore inventory = new IngredientStore(); // domain class. OKAY with new
	private static InventoryDaoImpl singleInventory;
	private static int DEFAULT_RELOAD_SIZE = 10;

	/**
	 * Instantiates the Inventory. The constructor is marked private to only 
	 * allow singleton instance of the class. This also initializes the inventory
	 * @see {@link #getInstance()}
	 */
	private InventoryDaoImpl() {
		reload();
	}

	
	/**
	 * Returns a singleton instance of this inventory
	 * @return the instance if already available else a new one
	 */
	public static InventoryDaoImpl getInstance() {
		
		if(null == singleInventory) {
			synchronized (InventoryDaoImpl.class) {
				if(null == singleInventory) {
					singleInventory = new InventoryDaoImpl();
				}
			}
		}
		return singleInventory;
	}


	/**
	 * Update the inventory by reducing the given ingredient with the number of units
	 * @param ingredientType the ingredient to update
	 * @param units the number of units to update/reduce
	 */
	public void reduce(IngredientType ingredientType, int units) {
		Map<IngredientType, Integer> ingredientMap = inventory.getIngredientAvailabilityMap();
		int available = ingredientMap.get(ingredientType);
		// update the available units
		if(available >= units) {
			available -= units;
			ingredientMap.put(ingredientType, available);
		} else {
			throw new ConcurrentInventoryUpdateException();
		}
	}

	/**
	 * Returns trus if stocks are available for the ingredient
	 * @param ingredient the given ingredient
	 * @param units the number of stocks for which availability is requested
	 * @return true if inventory has enough units for the indredient
	 */
	public boolean isAvailable(IngredientType ingredient, int units) {
		Map<IngredientType, Integer> ingredientMap = inventory.getIngredientAvailabilityMap();
		return ingredientMap.get(ingredient) >= units;
	}


	/**
	 * Reloads the inventory with the default reload size. This means every ingredient is
	 * reset to DEFAULT_RELOAD_SIZE
	 */
	public void reload() {
		IngredientType[] ingredientTypes = IngredientType.values();
		Map<IngredientType, Integer> ingredientMap = inventory.getIngredientAvailabilityMap();
		for (IngredientType ingredientType : ingredientTypes) {
//			Integer currentStock = ingredientMap.get(ingredientType);
//			if(null == currentStock) {
//				currentStock = 0;
//			}
//			currentStock += DEFAULT_RELOAD_SIZE;
//			ingredientMap.put(ingredientType, currentStock);
			ingredientMap.put(ingredientType, DEFAULT_RELOAD_SIZE);
		}
		
	}


	@Override
	public void accept(InventoryPrintVisitor visitor) {
		visitor.visit(inventory);
	}


	
}
