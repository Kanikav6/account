package com.assignment.newaccount.vo;

public class NewAccountRequest {

    public Long customerId;

    public double initialCredit;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }

    public NewAccountRequest() {
    }

    public NewAccountRequest(Long customerId, double initialCredit) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    @Override
    public String toString() {
        return "NewAccountRequest{" +
                "customerId='" + customerId + '\'' +
                ", initialCredit=" + initialCredit +
                '}';
    }
}
