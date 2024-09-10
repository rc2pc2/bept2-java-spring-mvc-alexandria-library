package org.lessons.java.alexandria.controller;

import java.util.List;

import org.lessons.java.alexandria.model.Book;
import org.lessons.java.alexandria.repo.BookRepository;
import org.lessons.java.alexandria.service.BookService;
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
@RequestMapping("/books")
public class BookController {
	//	repository field con autowired per d.i.
	
//	@Autowired
//	private BookRepository repo;
	
	private BookService service;
	
	@GetMapping()
	public String index(Model model) {	
		//	prendo i dati da consegnare a books/index
		List<Book> books = service.findAllSortedByRecent();

		//	li inserisco nel modello
		model.addAttribute("books", books);
		
		return "/books/index";
	}
	
	@GetMapping("/findByTitle/{title}")
	public String findByTitle(@PathVariable("title") String title , Model model) {
	   model.addAttribute("books", service.findAllByTitle(title));
	   return "/books/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id , Model model) {
	   model.addAttribute("book", service.getById(id));
	   return "/books/show";
	}
	
	// CREATE	
	
	@GetMapping("/create")
	public String create(Model model) {
	   model.addAttribute("book", new Book());
	   return "/books/create";
	}
	
	// STORE
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("book") Book formBook, 
						BindingResult bindingResult,
						Model model,
						RedirectAttributes redirectAttributes)
	{
		if (bindingResult.hasErrors()) {
			return "/books/create";
		} else {
			
			service.create(formBook);	
			redirectAttributes.addFlashAttribute("successMessage", formBook.getTitle() + " has been created successfully!");
			return "redirect:/books";
		}
	}
	
	// EDIT()    =>    una GET che restituisce al chiamate un form con i dati della risorsa richiesta
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
//		// trovo il libro
//		Book bookToEdit = repo.findById(id).get();
//		// lo inserisco nel model
//		model.addAttribute("book", bookToEdit);
		
		model.addAttribute("book", service.getById(id));
		
		// restituisco la view con il model inserito		
		return "/books/edit";
	}
	
	
	// UPDATE()  =>		una POST che aggiorna nel database con i nuovi dati inseriti, se corretti, la risorsa in esame
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("book") Book updatedFormBook, 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes)
	{
		//	se ci sono errori nel form
		if (bindingResult.hasErrors()) {
			// ritorna al form (e mostra gli errori)
			return "/books/edit";
		} else { // altrimenti
			//	prendi la mia repository particolare e aggiorna il libro con i nuovi dati convalidati
			service.update(updatedFormBook);
			
			redirectAttributes.addFlashAttribute("successMessage", updatedFormBook.getTitle() + " has been updated successfully!");
			
			// ridireziona l'utente alla index dei libri
			return "redirect:/books";
		}
	}
	
	// DELETE
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, 
			RedirectAttributes redirectAttributes) {
		
		// prendi la mia repository particolare e dopo aver trovato il mio libro attraverso il suo id, cancellalo dal database
		service.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "Book with id " + id + " has been deleted successfully!");
		
		// ridireziona l'utente alla index dei libri
		return "redirect:/books";
	}
	
	
	
	
	
	
	
	
	
}
