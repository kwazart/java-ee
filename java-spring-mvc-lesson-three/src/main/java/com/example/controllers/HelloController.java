package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String helloMethod(Model model) {
		model.addAttribute("name", "Student");
		return "hello_view";
	}
}
