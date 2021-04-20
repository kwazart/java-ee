package com.polozov.hibernate.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
	@Id
	@GeneratedValue
	@Column(name = "id")
	Long id;

	@Column(name = "title")
	String title;

	@OneToMany(mappedBy = "manufacturer")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private List<Product> products;

	@Formula("(SELECT avg(p.price) FROM products p WHERE p.manufacturer_id = id)")
	public BigDecimal avgManufacturerProductCost;

	public Manufacturer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Manufacturer{" +
				"id=" + id +
				", title='" + title + '\'' +
				// TODO разобраться, почему комментим
//				", products=" + products +
				'}';
	}
}
