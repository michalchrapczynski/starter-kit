package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.to.BookTo;

public interface BookService {

	List<BookTo> findAllBooks();

	List<BookTo> findBooksByTitle(String title);

	List<BookTo> findBooksByAuthor(String name);

	BookTo saveBook(BookTo book);

	void removeBook(BookTo book);

	void removeBookPoster(Long id);

	List<BookEntity> findBooksBySearchCriteria(BookSearchCriteria searchCriteria);

}
