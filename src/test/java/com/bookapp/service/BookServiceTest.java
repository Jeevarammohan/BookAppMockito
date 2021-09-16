package com.bookapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.exception.InvalidDataException;
import com.bookapp.model.Book;
import com.bookapp.repository.IBookRepository;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@Mock
	IBookRepository bookRepository;

	@InjectMocks
	BookServiceImpl bookService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	List<Book> bookList;
	Book book1, book2, book3, book4, book5, book6;

	@BeforeEach
	void setUp() throws Exception {
		bookService = new BookServiceImpl();
		bookService.setiBookRepository(bookRepository);
		book1 = new Book("Java", "Kathy", "Tech", 2300);
		book2 = new Book("Spring", "Joe", "Tech", 2300);
		book3 = new Book("Git", "Kathy", "Tech", 2300);
		book4 = new Book("Sql", "Jane", "DB", 2300);
		book5 = new Book("MVC", "Jane", "Microservice", 2300);
		book6 = new Book("Postgres", "Jane", "DB", 2300);
		bookList = Arrays.asList(book1, book2, book3, book4, book5, book6);
	}

	@AfterEach
	void tearDown() throws Exception {
		book1 = null;
		book2 = null;
		book3 = null;
		book4 = null;
		book5 = null;
		book6 = null;
	}

	@Test
	@DisplayName("Testing add book")
	void testAddBook() throws Exception {
		when(bookRepository.addBook(book1)).thenReturn(1);
		assertEquals(1, bookService.addBook(book1), "Not added");
	}

	@Test
	@DisplayName("Testing add book when Book data is invalid")
	void testAddBookWhenInvalidBook() throws Exception {
		when(bookRepository.addBook(book5)).thenThrow(InvalidDataException.class);
		assertThrows(InvalidDataException.class, () -> bookService.addBook(book5));
	}

	@Test
	@DisplayName("Testing Update Book")
	void testUpdateBook() {
		doNothing().when(bookRepository).updateBook(1, 2490);
		assertEquals("Book updated", bookService.updateBook(1, 2490), "Not updated");
	}

	@Test
	@DisplayName("Testing update book with invalid id")
	void testUpdateBookInvalidId() {
		assertEquals("Invalid data", bookService.updateBook(-1, 2490), "Not updated");
	}

	@Test
	@DisplayName("Testing update book with invalid price")
	void testUpdateBookInvalidPrice() {
		assertEquals("Invalid data", bookService.updateBook(1, -2490), "Not updated");
	}

	@Test
	@DisplayName("Testing delete book")
	void testDeleteBook() {
		doNothing().when(bookRepository).deleteBook(1);
		assertTrue(bookService.deleteBook(1));
	}

	@Test
	@DisplayName("Testing delete book with invalid id")
	void testDeleteBookWithInvalidId() {
		assertFalse(bookService.deleteBook(-1));
	}

	@Test
	@DisplayName("Testing get by author")
	void testGetByAuthor() {
		List<Book> books = Arrays.asList(book1, book3).stream().sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
		when(bookRepository.findByAuthor("Kathy")).thenReturn(books);
		assertEquals(books, bookService.getByAuthor("Kathy"));
	}

	@Test
	@DisplayName("Testing get by author-empty list")
	void testGetByAuthorEmptyList() {
		when(bookRepository.findByAuthor("Sam")).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class, () -> bookService.getByAuthor("Sam"));
	}

	@Test
	@DisplayName("Testing get by category")
	void testGetByCategory() {
		List<Book> books = Arrays.asList(book5, book6).stream().sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
		when(bookRepository.findByCategory("DB")).thenReturn(books);
		assertEquals(books, bookService.getByCategory("DB"));
	}

	@Test
	@DisplayName("Testing get by category- empty list")
	void testGetByCategoryEmptyList() {
		when(bookRepository.findByCategory("NONTECH")).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class, () -> bookService.getByCategory("NONTECH"));
	}

	@Test
	@DisplayName("Testing get by lesser price")
	void testGetByLesserPrice() {
		List<Book> books = bookList.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
		when(bookRepository.findByLesserPrice(4000)).thenReturn(books);
		assertEquals(books, bookService.getByLesserPrice(4000));

	}

	@Test
	@DisplayName("Testing get by lesser price - empty list")
	void testGetByLesserPriceEmptyList() {
		when(bookRepository.findByLesserPrice(2000)).thenReturn(new ArrayList<>());
		assertThrows(BookNotFoundException.class, () -> bookService.getByLesserPrice(2000));

	}

	@Test
	@DisplayName("Testing get by id")
	void testGetById() {
		when(bookRepository.findById(1)).thenReturn(book1);
		assertEquals(book1, bookService.getById(1));
	}

	@Test
	@DisplayName("Testing get by invalid id")
	void testGetByInvalidId() {
		when(bookRepository.findById(8)).thenReturn(null);
		assertThrows(IdNotFoundException.class, () -> bookService.getById(8));
	}

}
