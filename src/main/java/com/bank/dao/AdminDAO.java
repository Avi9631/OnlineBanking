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
	
	public List<Transaction> fetchTransactionOfUser(int acc) {
		List<Transaction> list= transacRepo.findAllByFrom(acc);
		List<Transaction> list2= transacRepo.findAllByTo(acc);
		for(Transaction t: list) {
			t.setType("DEBIT");
		}
		for(Transaction t: list2) {
			t.setType("CREDIT");
		}
		
		list.addAll(list2);
		list2=null;
		return list;
	}
	
	public void closeAcccount(int id) {
//		Optional<User> user= userRepo.findById(id);
		userRepo.deleteById(id);
//		accRepo.deleteById(user.get().getAccount().getAccno());
	}
	
	public User getUser(int id) {
		return userRepo.findById(id).get();
	}

}
