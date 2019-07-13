package com.nowcoder.project.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowcoder.project.model.Book;
import com.nowcoder.project.model.User;
import com.nowcoder.project.service.BookService;
import com.nowcoder.project.service.HostHolder;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private HostHolder hostHolder;

	/**
	 * 为model加载所有的book
	 */
	private void loadAllBooksView(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
	}

	@RequestMapping(path= {"/index"} ,method= {RequestMethod.GET})
	public String bookList(Model model) {
		User host = hostHolder.getUser();
		if (host != null) 
			model.addAttribute("host", host);
		loadAllBooksView(model);
		return "book/books";
	}

	@RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
	public String addBook() {
		return "book/addbook";
	}

	@RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
	public String doAddBook(@PathParam("name") String name ,
			@PathParam("author") String author, @PathParam("price") String price) {
		Book book = new Book();
		book.setName(name).setAuthor(author).setPrice(price);
		bookService.addBooks(book);
		return "redirect:/index";
	}

	/*
	 * 地址1：http://localhost:8989/SSSP/emps?pageNo=2
	 * 地址2：http://localhost:8989/SSSP/emp/7
	 * 如果想获取地址1中的 pageNo的值 ‘2’ ，则使用  @RequestParam ，
	 * 如果想获取地址2中的 emp/7 中的 ‘7 ’   则使用 @PathVariable		
		RequestParam  汉语意思就是： 请求参数。顾名思义 就是获取参数的 
		PathVariable  汉语意思是：路径变量。顾名思义，就是要获取一个url 地址中的一部分值
	 */
	@RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
	public String deleteBook(@PathVariable("bookId") int bookId) {
		bookService.deleteBooks(bookId);
		return "redirect:/index";
	}

	@RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
	public String recoverBook(@PathVariable("bookId") int bookId) {
		bookService.recoverBooks(bookId);
		return "redirect:/index";
	}



}




























