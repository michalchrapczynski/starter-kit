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

	private List<BookTo> allBooks;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params) {
		// ModelAndView mav = new ModelAndView("bookList");
		// mav.addObject("books", allBooks);
		allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}

	@RequestMapping(value = "/bookDelete/{id}", method = RequestMethod.GET)
	public String bookDelate(@ModelAttribute(value = "id") Long id, Map<String, Object> params) {
		if (!allBooks.isEmpty()) {
			for (BookTo bookTo : allBooks) {
				if (bookTo.getId() == id) {
					bookService.removeBook(bookTo);
					params.put("bookDelete", bookTo);
					break;
				}
			}
		}
		allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookDelete";
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public String bookAdd(@ModelAttribute(value = "title") String title,
			@ModelAttribute(value = "authors") String authors, Map<String, Object> params) {
		Long newID = (long) bookService.findAllBooks().size();

		BookTo bookAdd = new BookTo(newID + 1, title, authors);

		if (title.length() > 0 && authors.length() > 0) {
			bookService.saveBook(bookAdd);
		}
		allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}

}
