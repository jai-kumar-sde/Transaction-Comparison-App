package com.transactionservices.TransactionComparisonApplication.model;


public class Transaction {
    private double amount;
    private Metadata metadata;

    public Transaction() {
    }

    public Transaction(double amount, Metadata metadata) {
        this.amount = amount;
        this.metadata = metadata;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", metadata=" + metadata + "]";
    }
}
