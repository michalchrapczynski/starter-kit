package pl.spring.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	// @RequestMapping(value = "/books", method = RequestMethod.GET)
	// public String bookList(Map<String, Object> params) {
	// List<BookTo> allBooks = bookService.findAllBooks();
	// String authors = "";
	// for (BookTo bookTo : allBooks) {
	// authors = "";
	// int i = 0;
	// for (AuthorEntity author : bookTo.getAuthors()) {
	// authors += BookMapper.mapAuthors(bookTo.getAuthors().get(i).getAuthor());
	// i++;
	// }
	// }
	// System.out.println(authors);
	//
	// params.put("books", allBooks);
	// return "bookList";
	// }

	@RequestMapping(value = "/bookDelete/{id}", method = RequestMethod.GET)
	public String bookDelate(@ModelAttribute(value = "id") Long id, Map<String, Object> params) {
		List<BookTo> allBooks = bookService.findAllBooks();
		for (BookTo bookTo : allBooks) {
			if (bookTo.getId() == id) {
				bookService.removeBook(bookTo);
				params.put("bookDelete", bookTo);
				break;
			}
		}
		allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookDelete";
	}

}
