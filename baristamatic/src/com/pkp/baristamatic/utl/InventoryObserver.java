package com.pkp.baristamatic.utl;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.pkp.baristamatic.dao.InventoryDaoImpl;
import com.pkp.baristamatic.domain.Drink;
import com.pkp.baristamatic.domain.DrinkIngredient;

/**
 * The inventory stock observer
 *  
 * @author pravat
 */
public class InventoryObserver implements Observer {

	InventoryDaoImpl instance = InventoryDaoImpl.getInstance();
	/**
	 * Updates the inventory with the stocks for the given drink.
	 * It iterates thru the ingredients of the drink and reduces each
	 */
	public void update(Observable o, Object arg) {
		if(arg instanceof Drink) {
			Drink drink = (Drink) arg;
			List<DrinkIngredient> allIngredients = drink.getIngredients();
			for (DrinkIngredient ingredientSize : allIngredients) {
				instance.reduce(ingredientSize.getIngredient().getType(), ingredientSize.getUnits());
			}
		}
		
	}

	
}
