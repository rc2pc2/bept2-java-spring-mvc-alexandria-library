package org.lessons.java.alexandria.repo;

import org.lessons.java.alexandria.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Integer>  {
	// in automatico ho tutto il necessario, ma posso aggiungere eventuali metodi e funzionalita'
}
