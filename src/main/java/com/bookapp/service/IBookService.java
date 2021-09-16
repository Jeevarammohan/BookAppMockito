package com.bookapp.service;

import java.util.List;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.model.Book;

public interface IBookService {
	Integer addBook(Book book) throws Exception;

	String updateBook(int bookId, double price);

	boolean deleteBook(int bookId);

	List<Book> getByAuthor(String author) throws BookNotFoundException;

	List<Book> getByCategory(String category) throws BookNotFoundException;

	List<Book> getByLesserPrice(double price) throws BookNotFoundException;

	Book getById(int bookId) throws IdNotFoundException;

}
