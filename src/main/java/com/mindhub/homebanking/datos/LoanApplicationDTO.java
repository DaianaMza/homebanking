package com.mindhub.homebanking.datos;

public class LoanApplicationDTO {
    private String name;//nombre de los prestamos
    private double ammount;
    private int dues; //cuotas englizh pliz
    private String accountNumber;
    private double percentage;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(String name, double ammount, int dues, String accountNumber,double percentage) {
        this.name = name;
        this.ammount = ammount;
        this.dues = dues;
        this.accountNumber = accountNumber;
        this.percentage=percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public int getDues() {
        return dues;
    }

    public void setDues(int dues) {
        this.dues = dues;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getPercentage() {return percentage;}

    public void setPercentage(double percentage) {this.percentage = percentage;}
}
