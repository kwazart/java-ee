package com.polozov.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "catalogs")
@NamedQueries({
		@NamedQuery(name = "Catalog.findAll", query = "SELECT c FROM Catalog c"),
		@NamedQuery(name = "Catalog.findById", query = "SELECT c FROM Catalog c WHERE c.id = :id")
})
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	public Catalog() {
	}

	public Catalog(String title) {
		this.title = title;
	}

	public Catalog(Long id, String title) {
		this.id = id;
		this.title = title;
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

	@Override
	public String toString() {
		return "Catalog{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}
}
