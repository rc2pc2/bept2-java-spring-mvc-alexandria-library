package org.lessons.java.alexandria.repo;

import org.lessons.java.alexandria.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {
	// in automatico ho tutto il necessario, ma posso aggiungere eventuali metodi e funzionalita'
}
