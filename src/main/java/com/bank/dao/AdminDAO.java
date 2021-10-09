package com.bank.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.Account;
import com.bank.beans.LoanQuery;
import com.bank.beans.RaiseTicket;
import com.bank.beans.Transaction;
import com.bank.beans.User;
import com.bank.controllers.AccountRepository;
import com.bank.controllers.LoanRepository;
import com.bank.controllers.RaiseQueryRepository;
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
	@Autowired
	private RaiseQueryRepository queryRepo;
	@Autowired
	private LoanRepository loanRepo;
	
	public List<Account> showAllUserDetails() {
		return accRepo.findAll();
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
		userRepo.deleteById(id);
	}
	
	public User getUser(int id) {
		return userRepo.findById(id).get();
	}

	public List<Account> searchByAccount(int account) {
		return accRepo.findAllById(Arrays.asList(account));
	}
	
	public List<RaiseTicket> getAllQuery() {
		return queryRepo.findAll();
	}
	
	public RaiseTicket getQueryById(int id) {
		return queryRepo.findById(id).get();
	}
	
	public void updateQueryStatus(RaiseTicket query) {
		queryRepo.save(query);
	}
	
	public List<LoanQuery> getLoanApplicationdata() {
		return loanRepo.findAll();
	}
}
