package com.mindhub.homebanking.datos;

import com.mindhub.homebanking.models.Loan;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;
    private double percentage;

    @ElementCollection
    @Column(name = "payments")
    private List<Integer> payments;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
        this.percentage=loan.getPercentage();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public double getPercentage() {return percentage;}

    public void setPercentage(double percentage) {this.percentage = percentage;}
}
