package com.polozov.javaspringbootlessonfour.services;

import com.polozov.javaspringbootlessonfour.entities.Item;
import com.polozov.javaspringbootlessonfour.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
	private ItemsRepository itemsRepository;

	@Autowired
	public void setItemsRepository(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	public Page<Item> getAll() {
		System.out.println(itemsRepository.findByTitle("stone"));
		System.out.println(itemsRepository.findByCostBetween(25, 80));
		return itemsRepository.findAll(PageRequest.of(2, 3));
	}
}
