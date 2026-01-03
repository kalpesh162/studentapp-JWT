package com.exampple.student_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exampple.student_app.security.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response,
			Model model) {

		// Dummy authentication (replace with DB)
		if ("admin".equals(username) && "admin".equals(password)) {

			String token = jwtUtil.generateToken(username);

			Cookie cookie = new Cookie("JWT_TOKEN", token);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60); // 1 hour

			response.addCookie(cookie);

			return "redirect:/";
		}

		model.addAttribute("error", "Invalid Credentials");
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {

		Cookie cookie = new Cookie("JWT_TOKEN", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");

		response.addCookie(cookie);

		return "redirect:/login";
	}

}
