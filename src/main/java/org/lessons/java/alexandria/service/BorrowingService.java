package org.lessons.java.alexandria.service;

import java.util.List;

import org.lessons.java.alexandria.model.Borrowing;
import org.lessons.java.alexandria.repo.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
	
	@Autowired
	private BorrowingRepository repository;
	
	public List<Borrowing> findAllSortedByBorringDate(){
		return repository.findAll(Sort.by("borrowingDate"));
	}
	
	public Borrowing create(Borrowing borrowing) {
		return repository.save(borrowing);
	}
	
}
