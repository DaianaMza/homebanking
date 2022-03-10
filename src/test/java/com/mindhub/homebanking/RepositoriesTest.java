package com.mindhub.homebanking;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoanRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;



import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

//escanea en busqueda de @entitys y configura los reposjpa, hace que las operaciones sean por defecto transaccionales
//al correrlas son revertidas y no afectan datos reales fuerad e pruebas
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {
//    @Autowired
//    LoanRepository loanRepository;
//
//    @Test
//    public void existLoans(){
//     List<Loan> loans = loanRepository.findAll();
//        assertThat(loans,is(not(empty())));
//    }
//    @Test
//    public void existPersonalLoan(){
//        List<Loan> loans = loanRepository.findAll();
//        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
//
//    }
//1_verifica que existan prestamos en la base de datos y verifiqua que no este vacia 2_verifica que haya un prestamo llamado personal

}

