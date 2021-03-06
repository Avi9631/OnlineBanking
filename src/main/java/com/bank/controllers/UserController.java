package com.bank.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bank.beans.Account;
import com.bank.beans.Transaction;
import com.bank.beans.User;
import com.bank.dao.UserDAO;

@Controller
@ComponentScan("com.bank.dao")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@GetMapping("/")
	private String index() {
		return "index";
	}

	@GetMapping("/dashboard")
	private String dashboard() {
		return "dashboard";
	}

	@GetMapping("/fundtransfer")
	private String fundtransfer() {
		return "fundtransfer";
	}

	@GetMapping("/logout")
	private String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/login")
	private String login(@RequestParam("username") String email, @RequestParam("password") String password,
			HttpServletRequest request) {
		User user = userDAO.findUserByEmailPassword(email, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", user.getId());
			session.setAttribute("role", user.getRole());
			if(user.getRole().equals("USER")) {
				return "redirect:/dashboard";
			}else {
				return "redirect:/showall";
			}
			
		} else {
			return "redirect:/loginError";
		}
	}

	@PostMapping("/register")
	private String register(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("pin") String pin, @RequestParam("phone") String phone,
			@RequestParam("state") String state, @RequestParam("address") String address,
			@RequestParam("aadharproof") String aadharproof) {

		User user = new User(name, email, password, pin, phone, "USER", state, address, "Not Approved", aadharproof,
				null);
		String ifsc = "";
		switch (state) {
		case "Jharkhand":
			ifsc = "JHO5678";
			break;
		case "West Bengal":
			ifsc = "WBO5678";
			break;
		case "Orissa":
			ifsc = "ORO5678";
			break;
		case "Andhra Pradesh":
			ifsc = "APO5678";
			break;
		default:
			break;
		}
		Account account = new Account(10000000 + new Random().nextInt(90000000), LocalDateTime.now(), "SAVING", ifsc, 0,
				user);
		user.setAccount(account);
		userDAO.addUserToDB(user, account);
		return "redirect:/";
	}

	@GetMapping("/getBalance")
	@ResponseBody
	private String getBalance(@RequestParam("id") int id, @RequestParam("pin") String pin) {

		return userDAO.getBalance(id, pin);

	}

	@GetMapping("/profile")
	private String getUserProfileDetails(@RequestParam("id") int id, HttpServletRequest request) {
		request.setAttribute("usermodel", userDAO.getUser(id));
		return "profile";
	}

	@GetMapping("/passbook")
	private String getAllTransaction(@RequestParam("id") int id, HttpServletRequest request) {
		request.setAttribute("list", userDAO.getAllTransactions(id));
		return "passbook";
	}

	@PostMapping("/editprofile")
	private String editProfile(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("id") int id, @RequestParam("pin") int pin) {
		User u = userDAO.getUser(id);
		u.setEmail(email);
		u.setName(name);
		u.setAddress(address);
		u.setPassword(password);
		u.setPin(String.valueOf(pin));
		u.setPhone(phone);

		userDAO.updateUser(u);
		return "redirect:/profile?id=" + id;
	}

	@PostMapping("/transfer")
	private String transferFund(@RequestParam("id") int id, @RequestParam("accno") int accno,
			@RequestParam("ifsc") String ifsc, @RequestParam("accname") String accname,

			@RequestParam("amount") float amount, @RequestParam("mode") String mode, HttpServletRequest request) {

		
		if (userDAO.findAccount(accno).get()!=null) {

			User user = userDAO.getUser(id);
			if (user.getAccount().getBal() > 0) {
				Transaction trans = new Transaction();
				trans.setDate(LocalDateTime.now());
				trans.setAmount(amount);
				trans.setMode(mode);
				trans.setFrom(user.getAccount().getAccno());
				trans.setType("DEBIT");
				trans.setTo(accno);

				userDAO.transferFund(trans);
				request.setAttribute("msg", "Transaction successfull");
				return "transacstatus";
			} else {
				request.setAttribute("msg", "Insufficient Balance");
				return "transacstatus";
			}
		} else {
			request.setAttribute("msg", "Invalid Account");
			return "transacstatus";
		}

	}
			

}
