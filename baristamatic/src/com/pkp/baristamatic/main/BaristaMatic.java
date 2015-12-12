package com.pkp.baristamatic.main;

import java.util.Scanner;

import com.pkp.baristamatic.domain.DrinkType;
import com.pkp.baristamatic.domain.ServiceStatus;
import com.pkp.baristamatic.service.BaristaService;
import com.pkp.baristamatic.service.BaristaServiceImpl;

/**
 * Main class for the baristamatic application
 * @author pravat
 *
 */
public class BaristaMatic {
	public static void main(String[] args) {
		// get the instances
		BaristaService service = new BaristaServiceImpl();
		Scanner scanner = new Scanner(System.in);
		// print for the first time
		service.printInventoryAndMenu();
		while(true) {
			String command = scanner.nextLine();

			if(null != command && !command.trim().equals("")) { // if not blank line
				ServiceStatus status = null;
				DrinkType type = null;
				try {
					Integer drinkOrder = Integer.valueOf(command);
					type = DrinkType.getType(drinkOrder);
					if(null != type) {
						status = service.order(type);
					} else {
						status = ServiceStatus.INVALID_SELECTION;
					}
				} catch(NumberFormatException e) {
					if("q".equalsIgnoreCase(command)) {
						break;
					} else if("r".equalsIgnoreCase(command)) {
						status = service.reloadInventory();
					} else {
						status = ServiceStatus.INVALID_SELECTION;
					}
				}

				service.printMessage(status, command, type);
				// print after the order
				service.printInventoryAndMenu();
			} 
		}

		scanner.close();

	}
}
