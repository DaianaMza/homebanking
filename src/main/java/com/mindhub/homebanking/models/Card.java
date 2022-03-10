package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Boolean deleteCard;
    private CardType cardType;
    private CardColor cardColor;
    private String cardHolder;
    private String number;
    private int ccv;
    private LocalDateTime fromDate;
    private LocalDateTime trueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_card")
    private Client client;

    public Card() {
    }

    public Card(CardType cardType, CardColor cardColor, String cardHolder, String number, int ccv, LocalDateTime fromDate, LocalDateTime trueDate, Client client,Boolean deleteCard) {
        this.cardType = cardType;
        this.cardColor = cardColor;
        this.cardHolder = cardHolder;
        this.number = number;
        this.ccv = ccv;
        this.fromDate = fromDate;
        this.trueDate = trueDate;
        this.client = client;
        this.deleteCard=deleteCard;
    }

    public long getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getTrueDate() {
        return trueDate;
    }

    public void setTrueDate(LocalDateTime trueDate) {
        this.trueDate = trueDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getDeleteCard() {return deleteCard;}

    public void setDeleteCard(Boolean deleteCard) {this.deleteCard = deleteCard;}
}
