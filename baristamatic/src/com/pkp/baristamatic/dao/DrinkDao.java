package com.pkp.baristamatic.dao;

import com.pkp.baristamatic.dao.DrinkDaoImpl.IngredientConfig;
import com.pkp.baristamatic.domain.DrinkType;

/**
 * Dao for reading the configuration of a drink
 * @author pravat
 *
 */
public interface DrinkDao {
	
	/**
	 * Get the configuration of the drink. This method returns a list of
	 * ingredients-config instance
	 * @param type th type of the drink
	 * @return an array of IngredientConfig instances
	 */
	public IngredientConfig[] getDrinkConfig(DrinkType type);
}
