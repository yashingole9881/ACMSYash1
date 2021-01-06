package com.aadhar.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aadhar.app.model.User;
import com.aadhar.app.service.LoginService;
import com.aadhar.app.utility.Constants;
import com.aadhar.app.utility.GoogleAuthUtility;
import com.aadhar.app.utility.UserUtility;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private GoogleAuthUtility googleAuthUtility;

	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/signupform")
	public String showSignUpPage(Model theModel) {
		theModel.addAttribute("theUser", new User());
		return "signupform";
	}

	@PostMapping("/signupprocess")
	public String saveUser(@ModelAttribute("theUser") User theUser) {

		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		theUser.setAccountNonLocked(true);
		theUser.setEnabled(true);

		theUser.setSecretCode(googleAuthUtility.getSecretKeyUrl(theUser.getEmail()));
		theUser.setTwoFactorAuth(false);

		Date date = new Date();
		theUser.setCreatedDate(date);
		theUser.setModifiedDate(date);
		loginService.save(theUser);

		return "signupsuccess";
	}

	@GetMapping("/otpform")
	public String showOtpForm(HttpServletRequest request, HttpSession session, Model theModel) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			return "redirect:/login";
		}
		return "otpform";
	}

	@GetMapping("/authenticateotp")
	public String authenticateOTP(HttpServletRequest request, HttpSession session) {
		String otp = request.getParameter("otp");
		User user = (User) session.getAttribute("user");
		String key = googleAuthUtility.getKeyFromUrl(user.getSecretCode()); 
		System.out.println(key);
		if (googleAuthUtility.authenticated(key, Integer.parseInt(otp))) {
			if (UserUtility.getRoleFromUser(user).equals(Constants.ROLE_OWNER)) {
				return "redirect:/owner/dashboard";
			} else if (UserUtility.getRoleFromUser(user).equals(Constants.ROLE_USER)) {
				return "redirect:/user/dashboard";
			}
		} else {
			return "redirect:/otpform?error";
		}
		return "redirect:/otpform?error";
	}
}
