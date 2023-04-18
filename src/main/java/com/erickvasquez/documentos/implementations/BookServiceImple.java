package com.erickvasquez.documentos.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.erickvasquez.documentos.models.entities.Book;
import com.erickvasquez.documentos.services.BookService;

@Service
public class BookServiceImple implements BookService {

private static List<Book> library = new ArrayList<>();
	
	static {
		 library.add(new Book("0261102303", "Lord of the Rings", "otro"));
	     library.add(new Book("0007441428", "Game of Thrones", "otro"));
	     library.add(new Book("0747581088", "Harry Potter and the Half-Blood Prince", "otro"));
	     library.add(new Book("1401248195", "Watchmen", "otro"));
	     library.add(new Book("030788743X", "Ready player one 2", "otro"));
	     library.add(new Book("843760494X", "Cien Años de Soledad 2", "otro"));
	     library.add(new Book("0553804367", "A Briefer History of Time", "otro"));
	     
	}
	
	//method save
	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		Stream<Book> filteredStream = library.stream()
		.filter(b -> {
			 return !b.getIsbn().equals(book.getIsbn());
		});
		library = Stream.concat(filteredStream, Stream.of(book))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String isbn) {
		// TODO Auto-generated method stub
		library = library.stream()
			.filter(b -> !b.getIsbn().equals(isbn))
			.collect(Collectors.toList());
	}

	@Override
	public Book findOneById(String isbn) {
		// TODO Auto-generated method stub
		//findAny -> de la coleccion agarrara solo un objeto opcional
		return library.stream()
			.filter(b -> b.getIsbn().equals(isbn))
			.findAny()
			.orElse(null);
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return library;
	}	
}
