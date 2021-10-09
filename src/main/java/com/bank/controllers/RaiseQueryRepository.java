package com.bank.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.beans.RaiseTicket;

public interface RaiseQueryRepository extends JpaRepository<RaiseTicket, Integer> {

}
