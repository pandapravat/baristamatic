package com.pkp.baristamatic.dao;

import java.util.HashMap;
import java.util.Map;

import com.pkp.baristamatic.domain.DrinkType;
import com.pkp.baristamatic.domain.ingredient.IngredientType;

/**
 * Configuration for the drinks. New additions should be made to this class by adding new 
 * enums to DrinkType and updating the below static block.
 * 
 * @author pravat
 *
 */
public class DrinkDaoImpl implements DrinkDao {

	private static Map<DrinkType, IngredientConfig[]> drinkConfig;
	static {
		drinkConfig = new HashMap<DrinkType,  IngredientConfig[]>();

		//Coffee : 3 units of coffee, 1 unit of sugar, 1 unit of cream
		add(DrinkType.COFFEE, new IngredientConfig(IngredientType.COFFEE, 3),  
				new IngredientConfig(IngredientType.SUGAR, 1),  
				new IngredientConfig(IngredientType.CREAM, 1));
		//Decaf Coffee : 3 units of Decaf Coffee, 1 unit of sugar, 1 unit of cream
		add(DrinkType.DECAF_COFFEE, new IngredientConfig(IngredientType.DECAF_COFFEE, 3),  
				new IngredientConfig(IngredientType.SUGAR, 1),  
				new IngredientConfig(IngredientType.CREAM, 1));

		//Caffe Latte : 2 units of espresso, 1 unit of steamed milk
		add(DrinkType.CAFFE_LATTE, new IngredientConfig(IngredientType.ESPRESSO, 2),  
				new IngredientConfig(IngredientType.STEAMED_MILK, 1));

		//Caffe Americano : 3 units of espresso
		add(DrinkType.CAFFE_AMERICANO, new IngredientConfig(IngredientType.ESPRESSO, 3));

		//Caffe Mocha : 1 units of Espresso, 1 unit of cocoa, 1 unit of steamed milk, 1 unit of whipped cream
		add(DrinkType.CAFFE_MOCHA, new IngredientConfig(IngredientType.ESPRESSO, 1),  
				new IngredientConfig(IngredientType.COCOA, 1),
				new IngredientConfig(IngredientType.STEAMED_MILK, 1),
				new IngredientConfig(IngredientType.WHIPPED_CREAM, 1));

		//Cappuccino : 2 units of Espresso, 1 unit of steamed milk, 1 unit of foamed milk
		add(DrinkType.CAPPUCCINO, new IngredientConfig(IngredientType.ESPRESSO, 2),  
				new IngredientConfig(IngredientType.STEAMED_MILK, 1),
				new IngredientConfig(IngredientType.FOAMED_MILK, 1));
		
		// Add new drinks here
//		add(DrinkType.CHAI_LATTE, new IngredientConfig(IngredientType.ESPRESSO, 2),  
//				new IngredientConfig(IngredientType.STEAMED_MILK, 2),
//				new IngredientConfig(IngredientType.FOAMED_MILK, 2));

	}

	private static void add(DrinkType drinkType,
			IngredientConfig... ingredientConfig) {
		drinkConfig.put(drinkType, ingredientConfig);
	}


	public IngredientConfig[] getDrinkConfig(DrinkType type) {
		return drinkConfig.get(type);
	}

	// represents a IngredientType-Units combination
	public static class IngredientConfig {
		private IngredientType type;
		private int ingredientAmount;
		public IngredientConfig(IngredientType type, int ingredientAmount) {
			super();
			this.type = type;
			this.ingredientAmount = ingredientAmount;
		}
		public IngredientType getType() {
			return type;
		}
		public void setType(IngredientType type) {
			this.type = type;
		}
		public int getIngredientAmount() {
			return ingredientAmount;
		}
		public void setIngredientAmount(int ingredientAmount) {
			this.ingredientAmount = ingredientAmount;
		}

	}

	/**
	 * Returns a singleton instance of this dao
	 * @return the instance if already available else a new one
	 */
	public static DrinkDaoImpl getInstance() {

		if(null == singleInventory) {
			synchronized (InventoryDaoImpl.class) {
				if(null == singleInventory) {
					singleInventory = new DrinkDaoImpl();
				}
			}
		}
		return singleInventory;
	}

	private static DrinkDaoImpl singleInventory;

}
