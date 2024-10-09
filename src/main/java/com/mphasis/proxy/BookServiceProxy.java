package com.mphasis.proxy;

import java.util.Arrays;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mphasis.domain.Book;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="book-service")
public interface BookServiceProxy {
	
	@Retry(name = "book-service")
	@CircuitBreaker(name = "book-service", fallbackMethod = "FallBackGetBooktById")
	@GetMapping("/book/{id}")
	public Book getBooktById(@PathVariable Long id); 
	
	@Retry(name = "book-service")
	@CircuitBreaker(name = "book-service", fallbackMethod = "FallBackGetAllBooks")
	@GetMapping("/book")
	public List<Book> getAllBooks(); 
 
	default Book FallBackGetBooktById(Long id, Throwable cause) {
		
		System.out.println("some error: "+cause.getMessage());
		return new Book(id, "title", "author", "isbn", 100, 2100, "publisher"); 
		
	} 
 
	default List<Book> FallBackGetAllBooks(Throwable cause) {
		System.out.println("some error: "+cause.getMessage());
		return Arrays.asList();  
		
	}

}
