package com.techelevator.payment;

public abstract class PaymentProcessor {

    public void processPayment() {
        if (!authenticateToMerchant()) {
            // report could not authenticate
            return;
        }

        if (!detectFraud()) {
            // report fraud detected
            return;
        }

        if (!collectPayment()) {
            // report could not collect
        }
    }

    public abstract boolean authenticateToMerchant();
    public abstract boolean detectFraud();
    public abstract boolean collectPayment();

}
