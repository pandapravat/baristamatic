package com.pkp.baristamatic.domain;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.pkp.baristamatic.domain.ingredient.IngredientType;

/**
 * Represents an ingredient store. The store uses a treeMap with a comparator
 * which sorts the items in the Map ny the ingredient name
 * @author pravat
 *
 */
public class IngredientStore {
	private Map<IngredientType, Integer> ingredientAvailabilityMap;
	
	public IngredientStore() {
		ingredientAvailabilityMap = new TreeMap<IngredientType, Integer>(new Comparator<IngredientType>() {

			public int compare(IngredientType o1, IngredientType o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public Map<IngredientType, Integer> getIngredientAvailabilityMap() {
		return ingredientAvailabilityMap;
	}

	public void setIngredientAvailabilityMap(
			Map<IngredientType, Integer> ingredientAvailabilityMap) {
		this.ingredientAvailabilityMap = ingredientAvailabilityMap;
	}
	
	
}
