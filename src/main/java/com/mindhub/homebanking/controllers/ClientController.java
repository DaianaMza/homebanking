package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.datos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RestController

@RequestMapping("/api")

public class ClientController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }
    @RequestMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        ClientDTO client = new ClientDTO(clientRepository.findById(id).orElse(null));
        return client;
    }

    @RequestMapping("/clients/current")
    public ClientDTO GetCurrent(Authentication authentication){
        ClientDTO client = new ClientDTO(clientRepository.findByEmail(authentication.getName()));
        return client;
    }
        @PostMapping("/clients")
       // @RequestMapping(path = "/clients", method = RequestMethod.POST)
        public ResponseEntity<Object> register(
                @RequestParam String firstName, @RequestParam String lastName,
                @RequestParam String email, @RequestParam String password) {

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                return new ResponseEntity<>("Complete the spaces in blank", HttpStatus.FORBIDDEN);
            }
            if (clientRepository.findByEmail(email) != null) {
                return new ResponseEntity<>("The email is taken", HttpStatus.FORBIDDEN);
            }
            Client newClient = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
             Account account = accountRepository.save (new Account(AccountType.CURRENT,"ACC1", LocalDateTime.now(), 0.0, newClient,false));
            return new ResponseEntity<>("Registered! worray!!", HttpStatus.CREATED); //http 201
        }

    //@RequestMapping(path = "clients/current")
    //public ClientDTO getAll(Authentication authentication){
       //return new ClientDTO(clientRepository.findByEmail(authentication.getName()));}


    }
//  public List<Client>getClients(){return clientRepository.findAll();}

