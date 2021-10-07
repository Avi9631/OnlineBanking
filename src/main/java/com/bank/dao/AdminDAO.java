package com.bank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.Transaction;
import com.bank.beans.User;
import com.bank.controllers.AccountRepository;
import com.bank.controllers.TransactionRepository;
import com.bank.controllers.UserRepository;

@Service
public class AdminDAO {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private TransactionRepository transacRepo;
	
	public List<User> showAllUserDetails() {
		return userRepo.findAll();
	}
	
	

}
