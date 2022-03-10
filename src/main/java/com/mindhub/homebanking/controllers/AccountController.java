package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.datos.AccountDTO;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.mindhub.homebanking.repositories.AccountRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController

//@CrossOrigin(origins = "+", methods = {RequestMethod.GET,RequestMethod.POST})

@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        Account account= accountRepository.findById(id).orElse(null);
        AccountDTO accountDTO = new AccountDTO(account);
        return accountDTO;
    }
    @PostMapping("clients/current/accounts")
    //@RequestMapping(path = "clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication, @RequestParam AccountType accountType){
        Client client = clientRepository.findByEmail(authentication.getName());
        if (client.getAccounts().size() >=3){
            return new ResponseEntity<>("Tene ma de 3 cuenta ameo", HttpStatus.FORBIDDEN);}
        /////creo los numeros random
        Random num = new Random();
        int n= num.nextInt(99999999-1+1)+1;

        Account account=new Account(accountType,"VIN-"+n, LocalDateTime.now(),0.0,client,false);
        accountRepository.save(account);
          return  new ResponseEntity<>("", HttpStatus.CREATED);
    }
    @PatchMapping("clients/current/accounts/{id}")//modifica un elemento dentro de un objeto
    public ResponseEntity<Object> deleteAccount (@PathVariable Long id, @RequestParam Boolean estado){
        Account account= accountRepository.findById(id).orElse(null);

        account.setDeleteAccount(estado);
        accountRepository.save(account);
        return new ResponseEntity<>("tu tarjeta se fue exitosamente",HttpStatus.ACCEPTED);

    }


    };

