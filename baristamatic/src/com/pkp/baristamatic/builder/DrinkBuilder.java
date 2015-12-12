package com.pkp.baristamatic.builder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pkp.baristamatic.dao.DaoFactory;
import com.pkp.baristamatic.dao.DrinkDao;
import com.pkp.baristamatic.dao.DrinkDaoImpl.IngredientConfig;
import com.pkp.baristamatic.dao.InventoryDao;
import com.pkp.baristamatic.domain.Drink;
import com.pkp.baristamatic.domain.DrinkIngredient;
import com.pkp.baristamatic.domain.DrinkStore;
import com.pkp.baristamatic.domain.DrinkType;
import com.pkp.baristamatic.domain.ingredient.Ingredient;
import com.pkp.baristamatic.utl.DrinkStorePrintVisitor;
import com.pkp.baristamatic.utl.Visitee;

/**
 * The builder class for drinks
 * @author pravat
 *
 */
public class DrinkBuilder implements Builder<DrinkType, Drink>, Visitee<DrinkStorePrintVisitor> {

	/**
	 * Mapping between the drink type and the Drink
	 */
	private DrinkStore drinkStore;
	private InventoryDao inventory = DaoFactory.getInventoryDao(); // the singleton instance of the inventory
	private IngredientBuilder ingredientBuilder = IngredientBuilder.getInstance();
	private DrinkDao drinkDao = DaoFactory.getDrinkDao();;
	
	/**
	 * Instantiates the Drink Builder. The constructor is marked private to only 
	 * allow singleton instance of the class.
	 * @see {@link #getInstance()}
	 */
	private DrinkBuilder() {
		drinkStore = new DrinkStore();
		Map<DrinkType, Drink> typeToDrinkMap = drinkStore.getTypeToDrinkMap();
		
		// build the drink instances initially
		for (DrinkType aType : DrinkType.values()) {
			typeToDrinkMap.put(aType, build(aType));
		}
		// assigns drink number to the drinks
		int drinkNumber = 1;
		for (Entry<DrinkType, Drink> aType : typeToDrinkMap.entrySet()) {
			aType.getKey().setDrinkNumber(drinkNumber++);
		}
		
	}

	/**
	 * builds a drink. Uses the ingredient builder to
	 * nuild the ingredients
	 */
	public Drink build(DrinkType drinkType) {

		IngredientConfig[] config = drinkDao.getDrinkConfig(drinkType);
		Drink product = new Drink(drinkType);
		
		for (IngredientConfig ingredientConfig : config) {
			Ingredient build = ingredientBuilder.build(ingredientConfig.getType());
			product.addIngredient(build, ingredientConfig.getIngredientAmount());
		}
		
		return product;
	}
	
	public Drink getDrink(DrinkType type) {
		Map<DrinkType, Drink> typeToDrinkMap = drinkStore.getTypeToDrinkMap();
		return typeToDrinkMap.get(type);
	}
	
	
	/**
	 * Checks if a drink is available. Conceptually a drink is available
	 * if all the ingredients that make the drink is available. This is
	 * done by checking the availability of the ingredients
	 * 
	 * @param drink the drink in business
	 * @return true if all the ingredients that make the drink are available, false otherwise
	 */
	public boolean isAvailable(Drink drink) {
		List<DrinkIngredient> allIngredients = drink.getIngredients();
		for (DrinkIngredient ingredientSize : allIngredients) {
			Ingredient ingredient = ingredientSize.getIngredient();
			if(!inventory.isAvailable(ingredient.getType(), ingredientSize.getUnits())) {
				return false;
			}
		}
		return true;
	}
	

	private static DrinkBuilder drinkBuilder; // singleton instance
	/**
	 * Returns a singleton instance of the builder
	 * @return the instance
	 */
	public static DrinkBuilder getInstance() {
		if(null == drinkBuilder) {
			synchronized (DrinkBuilder.class) { 
				if(null == drinkBuilder) {
					drinkBuilder = new DrinkBuilder();
				}
			}
		}
		return drinkBuilder;
	}

	@Override
	public void accept(DrinkStorePrintVisitor visitor) {
		visitor.visit(drinkStore);
	}
}
