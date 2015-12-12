package com.pkp.baristamatic.dao;

/**
 * Factory clas for returning the dao implementaions
 * @author pravat
 *
 */
public class DaoFactory {
	public static InventoryDao getInventoryDao() {
		return InventoryDaoImpl.getInstance();
	}
	public static DrinkDao getDrinkDao() {
		return DrinkDaoImpl.getInstance();
	}
}
