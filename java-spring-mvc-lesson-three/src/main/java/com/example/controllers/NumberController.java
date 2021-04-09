package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// local..../number/[first | second | third]
@Controller
@RequestMapping("/number")
public class NumberController {

	@GetMapping("/first")
	public String firstMethod() {
		return "number-pages/first";
	}

	@GetMapping("/second")
	public String secondMethod() {
		return "number-pages/second";
	}

	@GetMapping("/third")
	public String thirdMethod() {
		return "number-pages/third";
	}

	@GetMapping("/fourth")
	public String fourthMethod(HttpServletRequest request, Model model) {
		String line = request.getParameter("line");
		model.addAttribute("line", line);
		return "number-pages/fourth";
	}

	@GetMapping("/fifth")
	public String fourthMethod(@RequestParam(value = "line", required = false) String line,
	                           Model model) {
		model.addAttribute("line", line);
		return "number-pages/fourth";
	}
}
