package com.pkp.baristamatic.domain;

/**
 * Represnts the status of a service request type
 * @author pravat
 */
public enum ServiceStatus {
	ORDER_PLACED, // order was placed successfullt
	OUT_OF_STOCK, // out of stock
	INVALID_SELECTION, // invalid selection
	DONE // the request was completed
}
