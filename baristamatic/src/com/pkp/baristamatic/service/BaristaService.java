package com.pkp.baristamatic.service;

import com.pkp.baristamatic.domain.DrinkType;
import com.pkp.baristamatic.domain.ServiceStatus;

/**
 * Service interface for Baristamatic
 * @author pravat
 *
 */
public interface BaristaService {
	public ServiceStatus printInventoryAndMenu();
	public ServiceStatus printMessage(ServiceStatus status, String command, DrinkType type);
	public ServiceStatus reloadInventory() ;
	public ServiceStatus order(DrinkType prd);
}
