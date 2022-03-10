package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double percentage;
    private String name;
    private double maxAmount;

    //personaliza las columnas y añade nuevo espacio en la memoria
    @ElementCollection
    @Column(name = "payments")
    private List<Integer> payments = new ArrayList<>();

    //Declara el dueño de la relacion
    @OneToMany(mappedBy = "loan" ,fetch = FetchType.EAGER)

    //Declara el espacio de memoria que le damos a client dentro del loan
    private Set<ClientLoan> clientLoans= new HashSet<>();

    public Loan() {
    }
    //  this.id = id; te da un error si lo dejas
    public Loan(String name, double maxAmount, List<Integer> payments,double percentage) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
        this.percentage=percentage;
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

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public double getPercentage() {return percentage;}

    public void setPercentage(double percentage) {this.percentage = percentage;}
}













