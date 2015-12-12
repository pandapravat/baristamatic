package com.pkp.baristamatic.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents an drink store. The store uses a treeMap with a comparator
 * which sorts the items in the Map ny the drink name
 * @author pravat
 *
 */
public class DrinkStore {
	private Map<DrinkType, Drink> typeToDrinkMap;
	public DrinkStore() {
		// create the tree map and using a comparator to sort the map
		typeToDrinkMap = new TreeMap<DrinkType, Drink>(new Comparator<DrinkType>() {

			public int compare(DrinkType o1, DrinkType o2) {
				return o1.getDrinkName().compareTo(o2.getDrinkName());
			}

		});
	}
	public Map<DrinkType, Drink> getTypeToDrinkMap() {
		return typeToDrinkMap;
	}
	public void setTypeToDrinkMap(Map<DrinkType, Drink> typeToDrinkMap) {
		this.typeToDrinkMap = typeToDrinkMap;
	}
}
