package com.transactionservices.TransactionComparisonApplication.model;

/**
 * This class represents the metadata for a message.
 */
public class Metadata {
    private int originatorId;
    private int destinationId;

    /**
     * Default constructor for Metadata class.
     */
    public Metadata() {
    }

    /**
     * Constructor for Metadata class.
     * 
     * @param originatorId The ID of the message originator.
     * @param destinationId The ID of the message destination.
     */
    public Metadata(int originatorId, int destinationId) {
        this.originatorId = originatorId;
        this.destinationId = destinationId;
    }

    /**
     * Getter for originatorId.
     * 
     * @return The ID of the message originator.
     */
    public int getOriginatorId() {
        return originatorId;
    }

    /**
     * Setter for originatorId.
     * 
     * @param originatorId The ID of the message originator.
     */
    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    /**
     * Getter for destinationId.
     * 
     * @return The ID of the message destination.
     */
    public int getDestinationId() {
        return destinationId;
    }

    /**
     * Setter for destinationId.
     * 
     * @param destinationId The ID of the message destination.
     */
    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    /**
     * Overrides the toString() method to provide a string representation of the Metadata object.
     * 
     * @return A string representation of the Metadata object.
     */
    @Override
    public String toString() {
        return "Metadata [originatorId=" + originatorId + ", destinationId=" + destinationId + "]";
    }
}
