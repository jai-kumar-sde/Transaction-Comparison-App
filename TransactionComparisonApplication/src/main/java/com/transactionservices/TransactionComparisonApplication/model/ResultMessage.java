package com.transactionservices.TransactionComparisonApplication.model;

public class ResultMessage {
    private Integer transactionId;
    private String comparisonStatus;
    private Double originalAmount;
    private Double receivedAmount;
	public ResultMessage() {
		super();
		this.transactionId = null;
		this.comparisonStatus = null;
		this.originalAmount = null;
		this.receivedAmount = null;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public String getComparisonStatus() {
		return comparisonStatus;
	}
	public void setComparisonStatus(String comparisonStatus) {
		this.comparisonStatus = comparisonStatus;
	}
	public Double getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}
	public Double getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	@Override
	public String toString() {
		return  "╔═══════════════════════════╗\n" +
                "║      Comparison Result    ║\n" +
                "╠═══════════════════════════╣\n" +
                "║ Transaction ID: " + transactionId + "         ║\n" +
                "║ Comparison Status: " + comparisonStatus + "  ║\n" +
                "║ Original Amount: $" + originalAmount + "        ║\n" +
                "║ Received Amount: $" + receivedAmount + "        ║\n" +
                "╚═══════════════════════════╝";
	}
	
    
}
