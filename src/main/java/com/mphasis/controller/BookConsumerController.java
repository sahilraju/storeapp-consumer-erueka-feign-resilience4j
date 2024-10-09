package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.domain.Book;
import com.mphasis.proxy.BookServiceProxy;

@RestController
@RequestMapping("/consumer/feign/book")
public class BookConsumerController {
	
	@Autowired
	private BookServiceProxy bookServiceProxy;
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		
		return bookServiceProxy.getBooktById(id);
		
	}
	
	@GetMapping
	public List<Book> getAllBooks() {
		
		return bookServiceProxy.getAllBooks(); 
		
	}

}
