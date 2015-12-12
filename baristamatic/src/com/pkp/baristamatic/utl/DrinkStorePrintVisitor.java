package com.pkp.baristamatic.utl;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import com.pkp.baristamatic.builder.DrinkBuilder;
import com.pkp.baristamatic.domain.Drink;
import com.pkp.baristamatic.domain.DrinkStore;
import com.pkp.baristamatic.domain.DrinkType;

/**
 * Print visitor for the Drink store
 * @author pravat
 *
 */
public class DrinkStorePrintVisitor implements Visitor<DrinkStore>{

	private static String DELIM = ",";
	// Currency format, hardcoding to US
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	DrinkBuilder builder = DrinkBuilder.getInstance();


	/**
	 * Prints the drinks
	 */
	@Override
	public void visit(DrinkStore drinkStore) {

		System.out.println("Menu : ");
		Map<DrinkType, Drink> typeToDrinkMap = drinkStore.getTypeToDrinkMap();
		for (Map.Entry<DrinkType, Drink> anEntry : typeToDrinkMap.entrySet()) {
			//print drinks
			Drink drink = anEntry.getValue();
			DrinkType drinkType = anEntry.getKey();
			// format the price as per the given currency
			String formatted =  currencyFormatter.format(drink.getPrice());
			System.out.println(drinkType.getDrinkNumber() + DELIM 
					+ drinkType.getDrinkName() + DELIM 
					+ formatted + DELIM
					+ builder.isAvailable(anEntry.getValue()));
		}

	}

}
