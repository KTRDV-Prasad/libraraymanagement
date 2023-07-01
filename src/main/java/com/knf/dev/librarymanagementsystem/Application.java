package com.knf.dev.librarymanagementsystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.entity.Role;
import com.knf.dev.librarymanagementsystem.entity.User;
import com.knf.dev.librarymanagementsystem.repository.UserRepository;
import com.knf.dev.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("AP1024", "Data Structures ", "21B81", "Algorithms");
			book.addAuthors(new Author("Jeffrey D", "Algorithms"));
			book.addCategories(new Category("Data Structures and Algorithms"));
			book.addPublishers(new Publisher("Ullman"));
			bookService.createBook(book);

			var book1 = new Book("BP1025", "Spring Microservices", "22A05", "Microservices");
			book1.addAuthors(new Author("Maxwell", "Microservices"));
			book1.addCategories(new Category("Electonics"));
			book1.addPublishers(new Publisher("Prasad"));
			bookService.createBook(book1);

			var book2 = new Book("DA2201", "Java Language", "241J8", "Programming");
			book2.addAuthors(new Author("Josh Lang", "Programming"));
			book2.addCategories(new Category("Coding Language"));
			book2.addPublishers(new Publisher("Bruce Eckel"));
			bookService.createBook(book2);

			var user = new User("admin", "admin", "prasad@gmail.com", passwordEncoder.encode("prasad571"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}
