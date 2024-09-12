package org.lessons.java.alexandria.controller;

import org.lessons.java.alexandria.model.Category;
import org.lessons.java.alexandria.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("categories", service.findAll());
		return "/categories/index";
	}
	
	@GetMapping("{id}")
	public String index(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("category", service.getById(id));
		return "/categories/show";
	}
	

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute(new Category());
		return "/categories/create";
	}
	

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("category") Category formCategory, 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "/categories/create";
		} 

		service.create(formCategory);
		redirectAttributes.addFlashAttribute("successMessage", formCategory.getName() + " has been created successfully!");
		return "redirect:/categories";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("category", service.getById(id));
		return "/categories/edit";
	}
	

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("category") Category formCategory, 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "/categories/edit";
		} 

		service.update(formCategory);
		redirectAttributes.addFlashAttribute("successMessage", formCategory.getName() + " has been updated successfully!");
		return "redirect:/categories";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, 
			RedirectAttributes redirectAttributes) {
		
		service.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "Category with id " + id + " has been deleted successfully!");
		
		return "redirect:/categories";
	}
}
