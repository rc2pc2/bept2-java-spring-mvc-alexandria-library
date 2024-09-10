package org.lessons.java.alexandria.controller;

import org.lessons.java.alexandria.model.Room;
import org.lessons.java.alexandria.repo.RoomRepository;
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
@RequestMapping("/rooms")
public class RoomController {
	
	@Autowired
	private RoomRepository repository; 
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("rooms", repository.findAll());
		return "/rooms/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id,
						Model model) {
		model.addAttribute("room", repository.findById(id).get());		
		return "/rooms/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("room", new Room());
		return "/rooms/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("room") Room formRoom, // recuperare il form appena popolato sottoforma di un modello Room
			BindingResult bindingResult,  // meccanismo di gestion degli errori
			Model model,  // il modello da consegnare eventualmente 
			RedirectAttributes redirectAttributes) { // meccanismo di gestione della messaggistica in redirect
		
		// verifico se ci siano errori nell'invio del form		
		if (bindingResult.hasErrors()) {
			return "/rooms/create";
		} 
		
		repository.save(formRoom);
		redirectAttributes.addFlashAttribute("successMessage", "The Room with name " + formRoom.getName() + " has been created successfully!");
		return "redirect:/rooms";
	}
	
	
	// EDIT & UPDATE
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("room", repository.findById(id).get());
		return "/rooms/edit";
	}
	
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("room") Room formRoom, // recuperare il form appena popolato sottoforma di un modello Room
			BindingResult bindingResult,  // meccanismo di gestione degli errori
			Model model,  // il modello da consegnare eventualmente 
			RedirectAttributes redirectAttributes) { // meccanismo di gestione della messaggistica in redirect
		
		// verifico se ci siano errori nell'invio del form		
		if (bindingResult.hasErrors()) {
			return "/rooms/edit";
		} 
		
		repository.save(formRoom);
		redirectAttributes.addFlashAttribute("successMessage", "The Room with name " + formRoom.getName() + " has been updated successfully!");
		return "redirect:/rooms";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("deletedMessage", "The Room has been removed successfully!");
		return "redirect:/rooms";
	}
}





























