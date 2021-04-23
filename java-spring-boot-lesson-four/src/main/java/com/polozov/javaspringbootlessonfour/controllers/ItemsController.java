package com.polozov.javaspringbootlessonfour.controllers;

import com.polozov.javaspringbootlessonfour.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {
	private ItemsService itemsService;

	@Autowired
	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@GetMapping
	public String showItems(Model model) {
		model.addAttribute("items", itemsService.getAll());
		return "product_views/items";
	}
}
