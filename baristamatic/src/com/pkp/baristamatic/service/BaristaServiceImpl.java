package com.pkp.baristamatic.service;

import java.util.Observable;

import com.pkp.baristamatic.builder.DrinkBuilder;
import com.pkp.baristamatic.dao.DaoFactory;
import com.pkp.baristamatic.dao.InventoryDao;
import com.pkp.baristamatic.domain.Drink;
import com.pkp.baristamatic.domain.DrinkType;
import com.pkp.baristamatic.domain.ServiceStatus;
import com.pkp.baristamatic.utl.DrinkStorePrintVisitor;
import com.pkp.baristamatic.utl.InventoryObserver;
import com.pkp.baristamatic.utl.InventoryPrintVisitor;

/**
 * Service class which servers any request requested by 
 * BaristaMatic application. This method implements Obserble to
 * keep track of changes when a drink is ordered
 * @author pravat
 *
 */
public class BaristaServiceImpl extends Observable implements BaristaService {
	
	DrinkBuilder drinkBuilder = DrinkBuilder.getInstance();
	InventoryDao inventory = DaoFactory.getInventoryDao();
	
	// visitors
	InventoryPrintVisitor inventoryVisitor = new InventoryPrintVisitor();
	DrinkStorePrintVisitor drinkStoreVisitor = new DrinkStorePrintVisitor();
	
	public BaristaServiceImpl() {
		InventoryObserver observer = new InventoryObserver();
		addObserver(observer);
	}
	
	/**
	 * Orders a drink. If the drink is available, the method orders it and then updates
	 * the ingredients. The updation is done by the  InventoryObserver
	 */
	public ServiceStatus order(DrinkType prd) {
		Drink drink = drinkBuilder.getDrink(prd);
		if(drinkBuilder.isAvailable(drink)) {
			this.setChanged();
			notifyObservers(drink);
			return ServiceStatus.ORDER_PLACED;
		} else {
			return ServiceStatus.OUT_OF_STOCK;
		}
	}
	
	/**
	 * Prints inventory and menu status
	 */
	public ServiceStatus printInventoryAndMenu() {
		// print the inventory
		inventory.accept(inventoryVisitor);
		// print the menu
		drinkBuilder.accept(drinkStoreVisitor);
		return ServiceStatus.DONE;
	}

	/**
	 * prints the message to console 
	 */
	public ServiceStatus printMessage(ServiceStatus status, String command, DrinkType type) {
		if(null != status) {
			switch (status) {
			case INVALID_SELECTION:
				System.out.println("Invalid selection: " + command);
				break;
			case OUT_OF_STOCK:
				System.out.println("Out of stock: " + type.getDrinkName()); // TODO make sysout
				break;
			case ORDER_PLACED:
				System.out.println("Dispensing: " + type.getDrinkName());
				break;

			default:
				break;
			}

		}
		return ServiceStatus.DONE;
		
	}
	
	/**
	 * reloads the inventory
	 */
	public ServiceStatus reloadInventory() {
		inventory.reload();
		return ServiceStatus.DONE;
	}
	
}
