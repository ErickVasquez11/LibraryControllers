package com.erickvasquez.documentos.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
	private String isbn;
	private String title;
	private String owner;
}
