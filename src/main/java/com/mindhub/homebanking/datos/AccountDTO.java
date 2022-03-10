package com.mindhub.homebanking.datos;
import com.mindhub.homebanking.models.Account;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.mindhub.homebanking.datos.TransactionDTO;
import com.mindhub.homebanking.models.AccountType;

public class AccountDTO {
    private long id;
    private AccountType accountType;
    private String number;
    private LocalDateTime creationDate =LocalDateTime.now();
    private double balance;
    private Set<TransactionDTO> transaction =new HashSet<>();
    private Boolean deleteAccount;
    public AccountDTO(){
    }

    public AccountDTO(Account account){
        this.id= account.getId();
        this.accountType=account.getAccountType();
        this.number= account.getNumber();
        this.creationDate= account.getCreationDate();
        this.balance= account.getBalance();
        this.transaction =account.getTransactions().stream().map(transaction ->new TransactionDTO(transaction)).collect(Collectors.toSet());
        this.deleteAccount=account.getDeleteAccount();
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransaction() {
        return transaction;}

    public void setTransaction(Set<TransactionDTO> transaction) {
        this.transaction = transaction;}

    public AccountType getAccountType() {return accountType;}

    public void setAccountType(AccountType accountType) {this.accountType = accountType;}

    public Boolean getDeleteAccount() {
        return deleteAccount;
    }

    public void setDeleteAccount(Boolean deleteAccount) {
        this.deleteAccount = deleteAccount;
    }
}
