package com.mindhub.homebanking;
import com.mindhub.homebanking.models.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HomebankingApplication {
    @Autowired
    private PasswordEncoder passwordEncoder; //habilita las paswords

	public HomebankingApplication(){
	}
	public static void main(String[] args) { SpringApplication.run(HomebankingApplication.class, args);}
	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository,
									   AccountRepository accountRepository,
									   TransactionRepository transactionRepository,
									   LoanRepository loanRepository,
									   ClientLoanRepository clientLoanRepository,
									   CardRepository cardRepository){
		return (args) -> {
			Client client1 = new Client ("Melba","Morel","melba@mindhub.com","1313");
            client1.setPassword(passwordEncoder.encode(client1.getPassword())); //ciframos las pass
			clientRepository.save(client1);

			Client client2 = new Client("Marian","Mul","mini@gmail.com","2345");
            client2.setPassword(passwordEncoder.encode(client2.getPassword()));
			clientRepository.save(client2);

			Account account1 = new Account(AccountType.CURRENT,"ACC1",LocalDateTime.now(),5000.0,client1,false);
			accountRepository.save(account1);

			LocalDateTime date = LocalDateTime.now();
			LocalDateTime currentDate = date.plusDays(1);
			Account account2 = new Account(AccountType.SAVING,"ACC2",currentDate,7000.0,client1,false);
			accountRepository.save(account2);

			Account account3 = new Account(AccountType.CURRENT,"ACC3",LocalDateTime.now(),19000.0,client2,false);
			accountRepository.save(account3);

			//Transacciones
			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 25000, "Sueldo",date,account1);
			transactionRepository.save(transaction1);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT,1200,"pago luz",date,account1);
			transactionRepository.save(transaction2);
			Transaction transaction3 = new Transaction(TransactionType.DEBIT,200,"kiosko Empanada",date,account1);
			transactionRepository.save(transaction3);
			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 5000, "PagoAmigo",date,account1);
			transactionRepository.save(transaction4);

			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 55000, "Sueldo",date,account2);
			transactionRepository.save(transaction5);
			Transaction transaction6 = new Transaction(TransactionType.DEBIT, 1200, "Telefono",date,account2);
			transactionRepository.save(transaction6);
			Transaction transaction7 = new Transaction(TransactionType.DEBIT, 25000, "Pago Haberes",date,account2);
			transactionRepository.save(transaction7);

			//Loans
			//Definimos las cuotas del Hipotecario,Personal y automotriz, asignandole prestamos a melba q se queda en bancarrota

			List<Integer> paymentsHipotecario = Arrays.asList(12,24,36,48,60);
			Loan loanHipotecario=new Loan("Mortgage",500000,paymentsHipotecario,15);
			loanRepository.save(loanHipotecario);

			List<Integer> paymentsPersonal = Arrays.asList(6,12,24);
			Loan loanPersonal=new Loan("Personal",100000,paymentsPersonal,25);
			loanRepository.save(loanPersonal);

			List<Integer> paymentsAutomotriz = Arrays.asList(6,12,24,36);
			Loan loanAutomotriz=new Loan("Auto",300000,paymentsAutomotriz,35);
			loanRepository.save(loanAutomotriz);

			ClientLoan clientLoan1 = new ClientLoan(400000,loanHipotecario.getPayments().get(4),client1,loanHipotecario);
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2 = new ClientLoan(50000,loanPersonal.getPayments().get(2),client1,loanPersonal);
			clientLoanRepository.save(clientLoan2);

			//Definimos las tarjetas
			LocalDateTime currentYears = date.plusYears(5);
			Card card1= new Card(CardType.DEBIT,CardColor.GOLD,client1.getFirstName()+" "+client1.getLastName(),"1234-7673-2222-3737",243,date,currentYears,client1,false);
			cardRepository.save(card1);
			//Tarjeta de credito titanium
			Card card2= new Card(CardType.CREDIT,CardColor.TITANIUM,client1.getFirstName()+" "+client1.getLastName(),"1432-5643-2266-8909",227,date,currentYears,client1,false);
			cardRepository.save(card2);
			//tarjeta de credito silver c2
			Card card3=new Card(CardType.CREDIT,CardColor.SILVER,client2.getFirstName()+" "+client2.getLastName(),"3333-4444-1212-6676",777,date,currentYears,client2,false);
			cardRepository.save(card3);
		};
	}
}
