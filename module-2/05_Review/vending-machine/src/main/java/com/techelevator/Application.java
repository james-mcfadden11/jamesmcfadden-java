package com.techelevator;

import java.io.File;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.vendingmachine.change.ChangeMaker;
import com.techelevator.vendingmachine.change.MinimumChangeMaker;
import com.techelevator.vendingmachine.inventory.FileInventory;
import com.techelevator.vendingmachine.inventory.Inventory;
import com.techelevator.vendingmachine.log.Auditable;
import com.techelevator.vendingmachine.log.FileAuditLog;
import com.techelevator.vendingmachine.view.PurchaseMenuCLI;
import com.techelevator.vendingmachine.view.VendingMachineCLI;
import com.techelevator.view.Menu;

public class Application {

	public static void main(String[] args) {	
		Inventory inventoryDao = new FileInventory(new File("vendingmachine.csv"));
		
		ChangeMaker changeMaker = new MinimumChangeMaker();

		Auditable auditLog = new FileAuditLog(new File("Log.txt"));
		
		VendingMachine vendingMachine = new VendingMachine(inventoryDao, changeMaker, auditLog);

		// Create CLIs
		Menu menu = new Menu(System.in, System.out);
		PurchaseMenuCLI purchaseMenuCLI = new PurchaseMenuCLI(menu, vendingMachine);
		VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenuCLI, vendingMachine);

		// Begin
		cli.run();
	}

}
