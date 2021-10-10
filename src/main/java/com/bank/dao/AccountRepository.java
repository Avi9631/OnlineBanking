package com.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.beans.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByUpi(String upi);
		

}
