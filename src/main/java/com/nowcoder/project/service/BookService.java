package com.nowcoder.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowcoder.project.dao.BoookDao;
import com.nowcoder.project.model.Book;
import com.nowcoder.project.model.enums.BookStatusEnum;

@Service
public class BookService {

	@Autowired
	private BoookDao bookDao;
	
	
	public List<Book> getAllBooks() {
		return bookDao.selectAll();
	}

	public int addBooks(Book book) {
		return bookDao.addBook(book);
	}

	public void deleteBooks(int id) {
		bookDao.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
	}

	public void recoverBooks(int id) {
		bookDao.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
	}

}
