package com.polozov.hibernate;

import com.polozov.hibernate.entities.Customer;
import com.polozov.hibernate.entities.Manufacturer;
import com.polozov.hibernate.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MainApp {
	public static void main(String[] args) {
		prepareData();
		work();
	}

	private static void prepareData() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();

		Session session = null;

		try {
			String sql = Files.lines(Paths.get("hibernate-block/create-and-fill.sql")).collect(Collectors.joining(" "));
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createNativeQuery(sql).executeUpdate();
			session.getTransaction().commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			factory.close();
			if (session != null) {
				session.close();
			}
		}
	}

	private static void work() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();

		Session session = null;

		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Product product = session.get(Product.class, 1L);
			System.out.println(product);
			Customer customer = session.get(Customer.class, 1L);
			System.out.println(customer);
			Manufacturer manufacturer = session.get(Manufacturer.class, 1L);
			System.out.println(manufacturer);
			System.out.println(manufacturer.getProducts());

			System.out.println("PRICE: " + manufacturer.avgManufacturerProductCost);
			session.getTransaction().commit();
		} finally {
			factory.close();
			if (session != null) {
				session.close();
			}
		}
	}
}
