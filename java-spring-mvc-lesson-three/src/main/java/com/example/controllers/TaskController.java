package com.example.controllers;

import com.example.model.Product;
import com.example.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class TaskController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String indexPage(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "task_views/index";
	}

	@GetMapping("/{id}")
	public String editProduct(@PathVariable(value = "id") Long id,
	                          Model model) {
		model.addAttribute("product", productRepository.findById(id));
		return "task_views/product_form";
	}

	@PostMapping("/product_update")
	public String updateProduct(Product product) {
		productRepository.update(product);
		return "redirect:/product";
	}

	@GetMapping("/new")
	public String newProduct(Model model) {
		// TODO: 09.04.2021 дописать добавление продукта
		return "task_views/product_form";
	}

	@GetMapping("/delete")
	public String removeProduct(Model model) {
		// TODO: 09.04.2021 дописать удаление продукта
		return "task_views/index";
	}
}
