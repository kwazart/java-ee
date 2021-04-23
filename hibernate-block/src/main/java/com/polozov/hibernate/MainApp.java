package com.polozov.hibernate;

import com.polozov.hibernate.entities.BigItem;
import com.polozov.hibernate.entities.Customer;
import com.polozov.hibernate.entities.Manufacturer;
import com.polozov.hibernate.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.OptimisticLockException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class MainApp {
	public static void main(String[] args) {
		prepareData();
//		work();

		optimisticVersioningTest();

		optimisticVersioningThreadingTest();
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

	private static void optimisticVersioningTest() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();

		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			BigItem bigItem = new BigItem(20);
			session.save(bigItem);
			System.out.println(bigItem);
			bigItem.setVal(25);
			System.out.println(bigItem);
			session.save(bigItem);
			System.out.println(bigItem);
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();
			bigItem = session.get(BigItem.class, 2L);
			System.out.println(bigItem);
			session.getTransaction().commit();
		} finally {
			factory.close();
			if (session != null) {
				session.close();
			}
		}
	}

	private static void optimisticVersioningThreadingTest() {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		try {
			new Thread(() -> {
				System.out.println("Thread #1 started");
				Session session = factory.getCurrentSession();
				session.beginTransaction();
				BigItem bigItem = session.get(BigItem.class, 1L);
				bigItem.setVal(100);
				uncheckableSleep(1000);
				session.save(bigItem);
				session.getTransaction().commit();;
				System.out.println("Thread #1 committed");
				if (session != null) {
					session.close();
				}
				countDownLatch.countDown();
			}).start();

			new Thread(() -> {
				System.out.println("Thread #2 started");
				Session session = factory.getCurrentSession();
				session.beginTransaction();
				BigItem bigItem = session.get(BigItem.class, 1L);
				bigItem.setVal(200);
				uncheckableSleep(3000);
				try {
					session.save(bigItem);
					session.getTransaction().commit();
					System.out.println("Thread #2 committed");
				} catch (OptimisticLockException e) {
					session.getTransaction().rollback();
					System.out.println("Thread #2 rollback");
					System.err.println(e.getMessage());
				}
				if (session != null) {
					session.close();
				}
				countDownLatch.countDown();
			}).start();
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("END");
		} finally {
			factory.close();
		}
	}

	public static void uncheckableSleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
