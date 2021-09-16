package com.bookapp.client;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.service.BookServiceImpl;
import com.bookapp.service.IBookService;

public class MainClient {

	public static void main(String[] args) {

		IBookService iBookService = new BookServiceImpl();
		Book book = new Book("Maven in action", "Peter", "Tech", 3000.56);
		try {
			System.out.println("Book is created with id " + iBookService.addBook(book));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("Book By Author");
		try {
			iBookService.getByAuthor("Peter").forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Book By Category");
		try {
			iBookService.getByCategory("Tech").forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Book By Price");
		try {
			iBookService.getByLesserPrice(4000).forEach(System.out::println);
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Book By id");
		try {
			System.out.println(iBookService.getById(3));
		} catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
