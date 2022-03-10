package com.mindhub.homebanking.models;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private AccountType accountType;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;
    private Boolean deleteAccount;

    //relacion con client
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private Set<Transaction> transactions=new HashSet<>();

    public Account() {
    }
    //setter y getter de client
    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account(AccountType accountType, String number, LocalDateTime creationDate, Double balance, Client client,Boolean deleteAccount) {
        this.accountType = accountType;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
        this.deleteAccount = deleteAccount;
    }
//setter and getter of transaccion a re el inglish


    public long getId() {
        return id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Boolean getDeleteAccount() {
        return deleteAccount;
    }

    public void setDeleteAccount(Boolean deleteAccount) {
        this.deleteAccount = deleteAccount;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", balance=" + balance +
                '}';
    }
}
