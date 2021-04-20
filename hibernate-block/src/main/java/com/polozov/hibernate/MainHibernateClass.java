package com.polozov.hibernate;

import com.polozov.hibernate.entities.Author;
import com.polozov.hibernate.entities.Book;
import com.polozov.hibernate.entities.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainHibernateClass {
	public static void main(String[] args) {
		Catalog catalog1 = new Catalog("Detective #1");
		Catalog catalog2 = new Catalog("Detective #2");
		Catalog catalog3 = new Catalog("Detective #3");
		Catalog catalog4 = new Catalog("Detective #4");

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Catalog.class)
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Reader.class)
				.buildSessionFactory();

		// CRUD
		Session session = null;

		try {
//			// CREATE
//			session = factory.getCurrentSession();
//
//			session.beginTransaction();
//			session.save(catalog1);
//			session.save(catalog2);
//			session.save(catalog3);
//			session.save(catalog4);
//			session.getTransaction().commit();
//
//			// READ
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			Catalog catalog5 = session.get(Catalog.class, 3L);
//			session.getTransaction().commit();
//			System.out.println(catalog5);
//
//			// UPDATE
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			Catalog catalog6 = session.get(Catalog.class, 2L);
//			Catalog catalog7 = new Catalog("Detective #5");
//			session.persist(catalog7);
//			catalog6.setTitle("Detective #21");
//			session.getTransaction().commit();
//			System.out.println(catalog6);
//
//			// DELETE
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			Catalog catalog8 = session.get(Catalog.class, 3L);
//			session.delete(catalog8);
//			session.getTransaction().commit();
//
//			// READ ALL
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			List<Catalog> listCatalog = session
//					.createQuery("from Catalog").getResultList();
//			System.out.println(listCatalog);
//			session.getTransaction().commit();
//
//			// READ + CONDITION
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			List<Catalog> catalogList = session
//					.createQuery("from Catalog c where c.title = :t")
//					.setParameter("t", "Detective #5")
//					.getResultList();
//			// from Catalog c where c.title = 'Detective #1' or c.id = 5
//			// from Catalog c where c.title LIKE 'Dete%'
//
//			System.out.println(catalogList);
//			session.getTransaction().commit();
//
//			// UPDATE/DELETE + CONDITION
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			session
//					.createQuery("update Catalog c set title = 'Fantasy #1' where c.id = 1")
//					.executeUpdate();
//			session
//					.createQuery("delete from Catalog where id = 2")
//					.executeUpdate();
//			session.getTransaction().commit();

			// добавим автора
			Author author = new Author();
			author.setName("Rowling");

			// добавим книги
			Book book1 = new Book();
			book1.setTitle("Harry Potter #1");
			book1.setAuthor(author);

			Book book2 = new Book();
			book2.setTitle("Harry Potter #2");
			book2.setAuthor(author);

			Book book3 = new Book();
			book3.setTitle("Harry Potter #3");
			book3.setAuthor(author);

			// добавим читателей
			Reader reader1 = new Reader();
			reader1.setName("Bill");
			reader1.setBooks(List.of(book1, book2));

			Reader reader2 = new Reader();
			reader2.setName("Bill");
			reader2.setBooks(List.of(book2, book3));

			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(author);
			session.save(book1);
			session.save(book2);
			session.save(book3);
			session.save(reader1);
			session.save(reader2);
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();
			Reader reader = session.get(Reader.class, 2L);
			Book book = session.get(Book.class, 1L);
			reader.getBooks().add(book);
			session.getTransaction().commit();

		} finally {
			session.close();
			factory.close();
		}
	}
}
