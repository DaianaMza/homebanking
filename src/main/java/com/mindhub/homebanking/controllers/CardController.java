package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.datos.CardDTO;
import com.mindhub.homebanking.datos.ClientDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.mindhub.homebanking.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RestController

@RequestMapping("/api")

public class CardController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/cards")
    public List<CardDTO> getCards(){
        return cardRepository.findAll().stream().map(cards-> new CardDTO(cards)).collect(toList());
    }
    @PostMapping("/clients/current/cards")
   // @RequestMapping(path= "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> registerCards(Authentication authentication,
            @RequestParam String cardType, @RequestParam String cardColor, @RequestParam Boolean deleteCard){
        Client client = clientRepository.findByEmail(authentication.getName());

 if (client.getCards().size() >=6){
        return new ResponseEntity<>("Tene ma de 3 cuenta ameo", HttpStatus.FORBIDDEN);}
 else {
     Random num = new Random();
     int n= num.nextInt(99999999-1+1)+1;

     Card card=new Card (CardType.valueOf(cardType),CardColor.valueOf(cardColor),client.getFirstName()+client.getLastName(),n+"",num.nextInt(999),LocalDateTime.now(),LocalDateTime.now().plusYears(5),client,false);
    cardRepository.save(card);
     return  new ResponseEntity<>("Se creo top ameo", HttpStatus.CREATED);
 }
    }
    @PatchMapping("/clients/current/cards/{id}")//modifica un elemento dentro de un objeto
    public ResponseEntity<Object> deleteCard (@PathVariable Long id, @RequestParam Boolean estado){
        Card card= cardRepository.findById(id).orElse(null);
       // Client client = clientRepository
        //cardRepository.deleteById(card.);
        card.setDeleteCard(estado);
        cardRepository.save(card);
        return new ResponseEntity<>("tu tarjeta se fue exitosamente",HttpStatus.ACCEPTED);

    }

    }




