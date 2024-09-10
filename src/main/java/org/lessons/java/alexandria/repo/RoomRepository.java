package org.lessons.java.alexandria.repo;

import org.lessons.java.alexandria.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer>  {
	// in automatico ho tutto il necessario, ma posso aggiungere eventuali metodi e funzionalita'
}
