package com.pkp.baristamatic.domain;

/**
 * Thrown when items in the inventory are updated concurrently
 * @author pravat
 *
 */
public class ConcurrentInventoryUpdateException extends RuntimeException{

	private static final long serialVersionUID = 2324L;
	public ConcurrentInventoryUpdateException() {
		super("Could not update inventory. Not enough stocks for the ingredient");
	}

}
