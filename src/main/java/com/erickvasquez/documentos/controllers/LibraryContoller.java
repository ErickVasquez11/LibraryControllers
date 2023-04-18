package com.erickvasquez.documentos.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.erickvasquez.documentos.models.dtos.SaveBookDTO;
import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.services.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/library")
public class LibraryContoller {
	
	// garantizar que todos los paquetes esten el el paquete principal
	@Autowired
	private BookService bookservice;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getBookList(Model model) {
    	model.addAttribute("books", bookservice.findAll());
    	return "book-list";
    }
    
    @GetMapping("/")
    public String getMainpage(Model model) {
    	String time = Calendar.getInstance().getTime().toString();
    	model.addAttribute("time", time);
    	
    	return "main";
    }
    
    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid SaveBookDTO bookInfo,
    		BindingResult validations, Model model) {
    	
    	Map<String, List<String>> mappedErrors = new HashMap<>();
    	validations.getFieldErrors()
    		.stream()
    		.forEach(error -> {
    			String key = error.getField() + "_errors";
    			List<String> errors = mappedErrors.get(key);
    			
    			if (errors == null) {
    				errors = new ArrayList<>();
    			}
    			
    			errors.add(error.getDefaultMessage());
    			
    			mappedErrors.put(key, errors);
    		});
    	
    	if(validations.hasErrors()) {
    		System.out.println("Hay errores");
    		
    		model.addAllAttributes(mappedErrors);
    		model.addAttribute("hasErrors", true);
    		
    		return "main";
    	}
    	
    	System.out.println(bookInfo);
    	
    	Book newBook = new Book(bookInfo.getIsbn(), bookInfo.getTitle(), bookInfo.getOwner());
    	bookservice.save(newBook);
    	
    	return "redirect:/library/all";
    }	
}
