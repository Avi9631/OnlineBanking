package com.bank.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.beans.Transaction;
import com.bank.beans.User;
import com.bank.dao.AdminDAO;

@Controller
@ComponentScan("com.bank.dao")
public class AdminController {
	
	@Autowired
	private AdminDAO adminDAO;
	
		

}
