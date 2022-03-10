package com.mindhub.homebanking.datos;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.mindhub.homebanking.datos.ClientLoanDTO;

public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //account, clientloan card dto
    private Set<AccountDTO> account=new HashSet<>();
    private Set<ClientLoanDTO> clientLoan=new HashSet<>();
    private Set<CardDTO> card =new HashSet<>();

    public ClientDTO(){
    }


    public ClientDTO(Client client){
        this.id =client.getId();
        this.firstName= client.getFirstName();
        this.lastName= client.getLastName();
        this.email= client.getEmail();
        this.password= client.getPassword();
        this.account= client.getAccounts().stream().map(account ->new AccountDTO(account)).collect(Collectors.toSet());
        this.clientLoan=client.getClientLoans().stream().map(clientLoan ->new ClientLoanDTO(clientLoan)).collect(Collectors.toSet());
        this.card=client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }

    public Set<ClientLoanDTO> getClientLoan() {
        return clientLoan;
    }

    public void setClientLoan(Set<ClientLoanDTO> clientLoan) {
        this.clientLoan = clientLoan;
    }

    public Set<CardDTO> getCard() {
        return card;
    }

    public void setCard(Set<CardDTO> card) {
        this.card = card;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }

    public void setAccount(Set<AccountDTO> account) {
        this.account = account;
    }

}
