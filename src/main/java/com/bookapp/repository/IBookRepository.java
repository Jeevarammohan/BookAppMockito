package com.bookapp.repository;

import java.util.List;

import com.bookapp.model.Book;

public interface IBookRepository {
	Integer addBook(Book book) throws Exception;

	void updateBook(int bookId, double price);

	void deleteBook(int bookId);

	List<Book> findByAuthor(String author);

	List<Book> findByCategory(String category);

	List<Book> findByLesserPrice(double price);

	Book findById(int bookId);

}
