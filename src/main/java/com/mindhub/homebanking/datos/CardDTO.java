package com.mindhub.homebanking.datos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDateTime;

public class CardDTO {
    private long id;

    private Boolean deleteCard;
    private CardType cardType;
    private CardColor cardColor;
    private String cardHolder;
    private String number;
    private int ccv;
    private LocalDateTime fromDate;
    private LocalDateTime trueDate;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardType = card.getCardType();
        this.cardColor = card.getCardColor();
        this.cardHolder = card.getCardHolder();
        this.number = card.getNumber();
        this.ccv = card.getCcv();
        this.fromDate = card.getFromDate();
        this.trueDate = card.getTrueDate();
        this.deleteCard=card.getDeleteCard();
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

    public Boolean getDeleteCard() {return deleteCard;}

    public void setDeleteCard(Boolean deleteCard) {this.deleteCard = deleteCard;}
}
