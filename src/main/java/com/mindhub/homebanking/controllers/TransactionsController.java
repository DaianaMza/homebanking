package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.datos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.mindhub.homebanking.datos.TransactionDTO;
@RestController
@RequestMapping("/api")
public class TransactionsController {
@Autowired
    private TransactionRepository transactionRepository;
@Autowired
    ClientRepository clientRepository;
@Autowired
    private AccountRepository accountRepository;

@Transactional
@PostMapping("/transactions")
//@RequestMapping(path ="/transactions", method = RequestMethod.POST)
public ResponseEntity<Object> transaction(Authentication authentication,@RequestParam Double ammount,
        @RequestParam String description, @RequestParam String originAccount, @RequestParam String destinyAccount){
    Client nuevoCliente = clientRepository.findByEmail(authentication.getName());
    List<Account> accountsClients = nuevoCliente.getAccounts().stream().collect(Collectors.toList());
    Account cuenta1 = accountRepository.findByNumber(originAccount);
    Account cuenta2= accountRepository.findByNumber(destinyAccount);
    double balance = cuenta1.getBalance()- ammount;
    double destinyBalance= cuenta2.getBalance()+ ammount;

if (ammount==null|| description.isEmpty()||originAccount.isEmpty()||destinyAccount.isEmpty()){
    return new ResponseEntity<>("Completa la cosa",HttpStatus.FORBIDDEN);
}
if (cuenta1==null){
    return new ResponseEntity<>("falta esta cuenta",HttpStatus.FORBIDDEN);
}
if (cuenta2==null){
    return new ResponseEntity<>("falta esta cuenta",HttpStatus.FORBIDDEN);
}
if (originAccount.equals(destinyAccount)){
    return new ResponseEntity<>("Estas haciendo una transaccion con la misma cuenta",HttpStatus.FORBIDDEN);
}
if (!accountsClients.contains(cuenta1)){
return new ResponseEntity<>("solo púedes hacer transacciones con tus cuentas",HttpStatus.FORBIDDEN);
}
if (cuenta1.getBalance() < ammount){
    return new ResponseEntity<>("te falta plata",HttpStatus.FORBIDDEN);
}
Transaction transaccion1 = new Transaction(TransactionType.DEBIT,- ammount," ",LocalDateTime.now(),cuenta1);
Transaction transaccion2 = new Transaction(TransactionType.CREDIT,+ ammount," ",LocalDateTime.now(),cuenta2);
transactionRepository.save(transaccion1);
transactionRepository.save(transaccion2);

cuenta1.setBalance(balance);
cuenta2.setBalance(destinyBalance);

return new ResponseEntity<>("FELICIDADE AMEO lavaste dinero!",HttpStatus.CREATED);
    //guardar transacciones y modificar el balance de la cuent
    //piolabank
    //ELBANKITO

}
};

