package org.lessons.java.alexandria.repo;

import java.util.List;

import org.lessons.java.alexandria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>  {
	// in automatico ho tutto il necessario, ma posso aggiungere eventuali metodi e funzionalita'
	
	public List<Book> findByTitle(String title);
	
	public List<Book> findByTitleContains(String title);
}
