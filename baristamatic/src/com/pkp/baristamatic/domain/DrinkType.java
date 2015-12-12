package com.pkp.baristamatic.domain;

/**
 * Represnts a Drink type
 * @author pravat
 */
public enum DrinkType {

	CAFFE_AMERICANO("Caffe Americano"), 
	CAFFE_LATTE("Caffe Latte"), 
	CAFFE_MOCHA("Caffe Mocha"), 
	CAPPUCCINO("Cappuccino"),
	COFFEE("Coffee"), 
	DECAF_COFFEE("Decaf Coffee");
	//CHAI_LATTE("Chai Latte");

	String drinkName;
	int drinkNumber;

	DrinkType(String drinkName) {
		this.drinkName = drinkName;
	}

	public String getDrinkName() {
		return drinkName;
	}

	public int getDrinkNumber() {
		return drinkNumber;
	}

	// this should be called dring runtime
	public void setDrinkNumber(int drinkNumber) {
		this.drinkNumber = drinkNumber;
	}

	// return the type of the drink given its number. The 
	// drinkNumber is decided by the collection which stores
	// the drinks
	public static DrinkType getType(Integer drinkOrder) {
		DrinkType[] values = values();
		for (DrinkType drinkType : values) {
			if(drinkType.drinkNumber == drinkOrder) return drinkType;
		}
		return null;
	}


}
