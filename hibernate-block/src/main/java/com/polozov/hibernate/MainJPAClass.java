package com.polozov.hibernate;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainJPAClass {
	public static void main(String[] args) {
		EntityManagerFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Catalog.class)
				.buildSessionFactory();

		EntityManager em = factory.createEntityManager();

		Catalog catalog1 = new Catalog("Horror #1");
		Catalog catalog2 = new Catalog("Horror #2");
		Catalog catalog3 = new Catalog("Horror #3");
		Catalog catalog4 = new Catalog("Horror #4");

		try {
			// CREATE
			em.getTransaction().begin();
			em.persist(catalog1);
			em.persist(catalog2);
			em.persist(catalog3);
			em.persist(catalog4);
			em.getTransaction().commit();

			// READ
			em.getTransaction().begin();
			Catalog catalog5 = em.find(Catalog.class, 2L);
			catalog5.setTitle("Horror - NEW");
			em.getTransaction().commit();
			catalog5.setTitle("Horror #10");

			// UPDATE
			em.getTransaction().begin();
			em.merge(catalog5);
			em.getTransaction().commit();

			// READ ALL
			em.getTransaction().begin();
			List<Catalog> list = em.createQuery("SELECT c FROM Catalog c").getResultList();
			System.out.println(list);
			em.getTransaction().commit();

			// READ by ID
			em.getTransaction().begin();
			Catalog catalog6 = em
					.createQuery("SELECT c FROM Catalog c WHERE c.id = :id", Catalog.class)
					.setParameter("id", 3L)
					.getSingleResult();
			System.out.println(catalog6);
			em.getTransaction().commit();

			// CREATE FROM DB by CONSTRUCTOR
			em.getTransaction().begin();
			String queryStr =
					"SELECT NEW Catalog(c.id, c.title) FROM Catalog AS c";

			List<Catalog> list1 = em.createQuery(queryStr, Catalog.class).getResultList();
			System.out.println(list1);
			em.getTransaction().commit();

			// NAMED QUERY
			em.getTransaction().begin();
			List<Catalog> list2 = em.createNamedQuery("Catalog.findAll", Catalog.class).getResultList();
			Catalog catalog7 = em.createNamedQuery("Catalog.findById", Catalog.class)
					.setParameter("id", 4L)
					.getSingleResult();

			System.out.println(list2);
			System.out.println(catalog7);
			em.getTransaction().commit();
		} finally {
			em.close();
			factory.close();
		}
	}
}
