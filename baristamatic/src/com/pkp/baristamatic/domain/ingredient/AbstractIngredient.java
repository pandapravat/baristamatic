package com.pkp.baristamatic.domain.ingredient;


/**
 * An abstract ingredient. All ingredients should implement this class. This
 * class implements the equals/hashCode methods to make this class to be put 
 * in Maps. This also implements the compareTo method for making it sortable
 * when put in clollections 
 * 
 * @author pravat
 */
public abstract class AbstractIngredient implements Ingredient {

	protected IngredientType type;
	protected float price;
	protected AbstractIngredient(IngredientType type, float price) {
		this.type = type;
		this.price = price;
	}

	public String getName() {
		return type.getName();
	}
	
	public IngredientType getType() {
		return type;
	}
	
	public float getPrice() {
		return price;
	}
}
