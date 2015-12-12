package com.pkp.baristamatic.domain.ingredient;

/**
 * Represents an ingredient type
 * @author pravat
 *
 */
public enum IngredientType {
	
	COFFEE("Coffee"), 
	DECAF_COFFEE("Decaf Coffee"),  
	SUGAR("Sugar"),
	CREAM("Cream"), 
	STEAMED_MILK("Steamed Milk"),
	FOAMED_MILK("Foamed Milk"), 
	ESPRESSO("Espresso"), 
	COCOA("Cocoa"), 
	WHIPPED_CREAM("Whipped Cream");
	
	private String name;
	
	IngredientType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
