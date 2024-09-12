package org.lessons.java.alexandria.service;

import java.util.List;

import org.lessons.java.alexandria.model.Category;
import org.lessons.java.alexandria.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category create(Category category) {
		return repository.save(category);
	}

	public Category update(Category category) {
		return repository.save(category);
	}

	public Category getById(Integer id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}
