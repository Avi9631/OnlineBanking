package com.bank.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.beans.LoanQuery;

public interface LoanRepository extends JpaRepository<LoanQuery, Integer> {

}
