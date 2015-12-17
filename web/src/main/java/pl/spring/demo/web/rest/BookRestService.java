package pl.spring.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RestController
@RequestMapping("/books")
public class BookRestService {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
	public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
		return bookService.findBooksByTitle(titlePrefix);
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public BookTo saveBook(@RequestBody BookTo book) {
		System.out.println(book.toString());
		return bookService.saveBook(book);
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
	public void removeBook(@PathVariable("bookId") Long bookId) {
		bookService.removeBookPoster(bookId);
	}

	@RequestMapping(value = "/book", method = RequestMethod.PUT)
	public BookTo bookUpdate(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

}
