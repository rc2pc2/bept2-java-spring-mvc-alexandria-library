package org.lessons.java.alexandria.controller;

import java.time.LocalDate;

import org.lessons.java.alexandria.model.Book;
import org.lessons.java.alexandria.model.Borrowing;
import org.lessons.java.alexandria.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {
	
	@Autowired
	private BorrowingService service;
		
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("borrowings", service.findAllSortedByBorringDate());
		return "/borrowings/index";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("borrowing") Borrowing formBorrowing, 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "/borrowings/create";
		} 
		
		formBorrowing.setBorrowingDate(LocalDate.now());
		service.create(formBorrowing);
		
		return "redirect:/books/" + formBorrowing.getBook().getId();
	}
}
