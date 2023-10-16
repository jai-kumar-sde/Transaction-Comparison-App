package com.transactionservices.TransactionComparisonApplication.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionMessage {
	
    @JsonProperty("PID")
    private Integer pId;

    @JsonProperty("PAMOUNT")
    private Double pAmount;

    @JsonProperty("PDATA")
    private Long pData;

    public TransactionMessage() {
        // Default constructor
    }

    public TransactionMessage(Integer pId, Double pAmount, Long pData) {
        this.pId = pId;
        this.pAmount = pAmount;
        this.pData = pData;
    }

    public Integer getPid() {
        return pId;
    }

    public void setPid(Integer pId) {
        this.pId = pId;
    }

    public Double getpAmount() {
        return pAmount;
    }

    public void setpAmount(Double pAmount) {
        this.pAmount = pAmount;
    }

    public Long getpData() {
        return pData;
    }

    public void setpData(Long pData) {
        this.pData = pData;
    }

    @Override
    public String toString() {
        return "TransactionMessage [pid=" + pId + ", pAmount=" + pAmount + ", pData=" + pData + "]";
    }
}
