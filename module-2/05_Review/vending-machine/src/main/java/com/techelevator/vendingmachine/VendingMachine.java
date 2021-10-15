package com.techelevator.vendingmachine;

import java.math.BigDecimal;

import com.techelevator.snack.Snack;
import com.techelevator.vendingmachine.change.Change;
import com.techelevator.vendingmachine.change.ChangeMaker;
import com.techelevator.vendingmachine.exception.InsufficientFundsException;
import com.techelevator.vendingmachine.exception.InvalidSlotLocationException;
import com.techelevator.vendingmachine.exception.SoldOutException;
import com.techelevator.vendingmachine.inventory.Inventory;
import com.techelevator.vendingmachine.inventory.Slot;
import com.techelevator.vendingmachine.log.Auditable;

public class VendingMachine {
	private BigDecimal balance;
	private final Inventory inventoryDao;
	private final ChangeMaker changeMaker;
	private final Auditable auditLog;

	/**
	 * Get the balance remaining.
	 * 
	 * @return The balance remaining.
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	public VendingMachine(Inventory inventoryDao, ChangeMaker changeMaker, Auditable auditLog) {
		balance = new BigDecimal("0.00");
		
		this.inventoryDao = inventoryDao;
		inventoryDao.restock();
		
		this.changeMaker = changeMaker;
		this.auditLog = auditLog;
	}

	@Override
	public String toString() {
		StringBuilder displayString = new StringBuilder();
		for (String slotName : inventoryDao.getInventory().keySet()) {
			Slot currSlot = inventoryDao.getInventory().get(slotName);

			displayString.append(currSlot.toString()).append(System.lineSeparator());
		}

		return displayString.toString();
	}

	/**
	 * Attempt to feed money into the machine.
	 * 
	 * @param amount The amount of money to feed.
	 * @return True if money was successfully added. False otherwise.
	 */
	public boolean feedMoney(BigDecimal amount) {
		if (!isValidAmount(amount))
			return false;

		balance = balance.add(amount);

		auditLog.logMoneyFed(amount, balance);

		return true;
	}

	private boolean isValidAmount(BigDecimal amount) {
		if (amount == null)
			return false;

		if (amount.compareTo(BigDecimal.valueOf(1)) == 0) { // 1 Dollar
			return true;
		} else if (amount.compareTo(BigDecimal.valueOf(2)) == 0) { // 2 Dollars
			return true;
		} else // 10 Dollars
			if (amount.compareTo(BigDecimal.valueOf(5)) == 0) { // 5 Dollars
			return true;
		} else return amount.compareTo(BigDecimal.valueOf(10)) == 0;
	}

	/**
	 * Purchase an item from a slot within the machine.
	 * 
	 * @param slotName The name of the slot to purchase from.
	 * @return Details about the purchase
	 */
	public Snack purchaseItem(String slotName) {
		if (slotName == null || !inventoryDao.getInventory().containsKey(slotName)) {
			throw new InvalidSlotLocationException(slotName + " is invalid");
		}

		Slot slot = inventoryDao.getInventory().get(slotName);

		if (slot.getQuantity() == 0) {
			throw new SoldOutException();
		}

		if (balance.compareTo(slot.getPrice()) < 0) {
			throw new InsufficientFundsException();
		}

		Snack item = slot.vend();
		BigDecimal balancePrior = balance;
		balance = balance.subtract(item.getPrice());

		auditLog.logPurchaseMade(slotName + " " + item.getName(), balancePrior, balance);

		return item;
	}

	/**
	 * Finish the transaction and return change to the consumer.
	 * 
	 * @return The amount of change the consumer is owed using the fewest coins
	 *         possible.
	 */
	public Change finishTransaction() {
		Change change = changeMaker.getChangeFor(balance);

		auditLog.logTransactionFinished(balance, BigDecimal.ZERO);

		balance = BigDecimal.ZERO;

		return change;
	}
}
