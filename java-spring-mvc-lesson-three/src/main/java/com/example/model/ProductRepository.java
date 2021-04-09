package com.example.model;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// CRUD
@Repository
public class ProductRepository {

	private static AtomicLong identity = new AtomicLong(0);

	private final Map<Long, Product> identityMap = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		add(new Product(null, "MacBook", "Ultra low and Great Power", new BigDecimal(3000)));
		add(new Product(null, "iPhone", "The most expensive phone by credit", new BigDecimal(1000)));
		add(new Product(null, "iPad", "More size - more cost", new BigDecimal(2000)));
	}

	public void add(Product product) {
		product.setId(identity.incrementAndGet());
		identityMap.put(product.getId(), product);
	}

	public void update(Product product) {
		identityMap.put(product.getId(), product);
	}

	public void remove(long id) {
		identityMap.remove(id);
	}

	public Product findById(long id) {
		return identityMap.get(id);
	}

	public List<Product> findAll() {
		return new ArrayList<>(identityMap.values());
	}
}
