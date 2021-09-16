package com.bookapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.model.Book;

public class BookRepositoryImpl implements IBookRepository {
	static Connection connection;

	@Override
	public Integer addBook(Book book) throws Exception{
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		int bookId=0;
		String insertQuery = "insert into book(title,author,category,price) values(?,?,?,?);";
		try {
			preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setDouble(4, book.getPrice());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while(resultSet.next()) {
				bookId=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BookDAO.closeConnection();

		}
		return bookId;
	}

	@Override
	public void updateBook(int bookId, double price) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		String updateQuery = "update book set price=? where bookId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setDouble(1, price);
			preparedStatement.setInt(2, bookId);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			BookDAO.closeConnection();

		}

	}

	@Override
	public void deleteBook(int bookId) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		String deleteQuery = "delete from book where bookId=?";
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, bookId);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BookDAO.closeConnection();
			
		}

	}

	@Override
	public List<Book> findByAuthor(String author) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		List<Book> booksByAuthor = new ArrayList<>();
		String selectQuery = "select * from book where author=?";
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, author);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setCategory(resultSet.getString(4));
				book.setPrice(resultSet.getDouble(5));
				booksByAuthor.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BookDAO.closeConnection();

		}
		return booksByAuthor;
	}

	@Override
	public List<Book> findByCategory(String category) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		List<Book> booksByCategory = new ArrayList<>();
		String selectQuery = "select * from book where category=?";
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setCategory(resultSet.getString(4));
				book.setPrice(resultSet.getDouble(5));
				booksByCategory.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			BookDAO.closeConnection();

		}
		return booksByCategory;
	}

	@Override
	public List<Book> findByLesserPrice(double price) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		List<Book> booksByLesserPrice = new ArrayList<>();
		String selectQuery = "select * from book where price<?";
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDouble(1, price);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setCategory(resultSet.getString(4));
				book.setPrice(resultSet.getDouble(5));
				booksByLesserPrice.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BookDAO.closeConnection();

		}
		return booksByLesserPrice;
	}

	@Override
	public Book findById(int bookId) {
		connection = BookDAO.openConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from book where bookId=?";
		Book book = null;
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setDouble(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book = new Book();
				book.setBookId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setAuthor(resultSet.getString(3));
				book.setCategory(resultSet.getString(4));
				book.setPrice(resultSet.getDouble(5));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BookDAO.closeConnection();

		}
		return book;

	}

}
