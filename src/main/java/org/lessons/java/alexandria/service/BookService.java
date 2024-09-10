package org.lessons.java.alexandria.service;

import java.time.Instant;
import java.util.List;

import org.lessons.java.alexandria.model.Book;
import org.lessons.java.alexandria.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public List<Book> findAllSortedByRecent(){
		return repository.findAll(Sort.by("updatedAt"));
	}
	
	public List<Book> findAllByTitle(String searchedText){
		return repository.findByTitleContains(searchedText);
	}
	
	public Book getById(Integer id) {
		return repository.findById(id).get();
	}
	
	public Book create(Book book) {
		return repository.save(book);
	}
	
	public Book update(Book book) {
		book.setUpdatedAt(Instant.now());
		return repository.save(book);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);;
	}
		
	//	se esiste
	//  ricerche particolare
}
