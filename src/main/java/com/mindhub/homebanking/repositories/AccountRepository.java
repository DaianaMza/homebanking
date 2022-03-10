package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface AccountRepository  extends JpaRepository<Account,Long>{
   Account findByNumber (String number);

   //objeto de tipo account con el nombre de find numbre que recibe como paramatrotf el string number
}
