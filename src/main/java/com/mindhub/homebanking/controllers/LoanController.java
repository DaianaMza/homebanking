package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.datos.ClientDTO;
import com.mindhub.homebanking.datos.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.datos.LoanApplicationDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;
@Autowired
    AccountRepository accountRepository;
@Autowired
    ClientRepository clientRepository;
@Autowired
    TransactionRepository transactionRepository;
@Autowired
   ClientLoanRepository clientLoanRepository;


    @GetMapping("/loan")
    public List<LoanDTO> getLoans(){
        return loanRepository.findAll().stream().map(loan-> new LoanDTO(loan)).collect(toList());
    }

    @Transactional
    @PostMapping("/loans")
    //@RequestMapping(value = "/loans", method = RequestMethod.POST)
    public ResponseEntity<Object> addloan(Authentication authentication,@RequestBody LoanApplicationDTO loanApplicationDTO) {

        //Loan getLoanName = loanRepository.findByName(loanApplicationDTO.getAccountNumber());//nombre de los prestamos
        Loan getLoanName = loanRepository.findByName(loanApplicationDTO.getName());
        Account getAccount = accountRepository.findByNumber(loanApplicationDTO.getAccountNumber());
        Client clientAuthenticated = clientRepository.findByEmail(authentication.getName());

        if (loanApplicationDTO==null||loanApplicationDTO.getAmmount()==0||loanApplicationDTO.getDues()==0){
            return new ResponseEntity<>("Completa los datos faltantes bobo", HttpStatus.FORBIDDEN);
        }
        if (getLoanName==null){
            return new ResponseEntity<>("El prestamo no existe cara nabo", HttpStatus.FORBIDDEN);
        }
        if (getLoanName.getMaxAmount()<loanApplicationDTO.getAmmount()){
            return new ResponseEntity<>("El monto solicitado exede el monto max del prestamo", HttpStatus.FORBIDDEN);
        }
       // !=null
        if (!getLoanName.getPayments().contains(loanApplicationDTO.getDues())){
            return new ResponseEntity<>("las cuotas no se encuentran disponibles",HttpStatus.FORBIDDEN);
        }
        if (getAccount==null){
            return new ResponseEntity<>("la cuenta no existe cara nabo", HttpStatus.FORBIDDEN);
        }
        if (!clientAuthenticated.getAccounts().contains(getAccount)){
            return new ResponseEntity<>("la cuenta de destino no pertenece a un cliente autentificado", HttpStatus.FORBIDDEN);
        }
        //get account porque necesito obetenr y verificar si en el conjunto de cuentas del cliente

      Double porcentaje = 1.+loanApplicationDTO.getPercentage();
        Double extraPorcentaje =loanApplicationDTO.getAmmount()*porcentaje;


        ClientLoan clientLoan = new ClientLoan(extraPorcentaje,loanApplicationDTO.getDues(),clientAuthenticated,getLoanName);
        clientLoanRepository.save(clientLoan);

       //ClientLoan clientLoan =new ClientLoan(loanApplicationDTO.getAmmount(),loanApplicationDTO.getDues(),clientAuthenticated,getLoanName);
           // clientLoanRepository.save(clientLoan);

        Transaction transactionCredit = new Transaction(TransactionType.CREDIT,loanApplicationDTO.getAmmount(),getLoanName.getName()+"Se ah aprobado el prestamo loko", LocalDateTime.now(),getAccount);
        transactionRepository.save(transactionCredit);
        getAccount.setBalance(getAccount.getBalance()+loanApplicationDTO.getAmmount());
                //instanciar es colocar una clase y designacion igual a new
        return new ResponseEntity<>("FELICIDADE AMEO TE ENDEUDASTE HASTA EL PINGO",HttpStatus.CREATED);
    }


}
