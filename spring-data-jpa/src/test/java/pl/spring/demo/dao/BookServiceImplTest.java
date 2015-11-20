package pl.spring.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../service/CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	BookService bookService;

	@Test
	public void testShouldFindBookWhenOnlyTitle() {
		// given
		BookSearchCriteria searchCriteria = new BookSearchCriteria();
		searchCriteria.setTitle("Pierwsza książka");
		// when
		List<BookEntity> books = new ArrayList<BookEntity>();
		books = bookService.findBooksBySearchCriteria(searchCriteria);
		// then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		BookEntity bookEntity = books.get(0);
		assertEquals("Pierwsza książka", bookEntity.getTitle());
	}

	@Test
	public void testShouldFindBookWhenTitleAndLibrary() {
		// given
		BookSearchCriteria searchCriteria = new BookSearchCriteria();
		searchCriteria.setTitle("Pierwsza książka");
		searchCriteria.setLibraryName("Politechnika Wrocławska");
		// when
		List<BookEntity> books = new ArrayList<BookEntity>();
		books = bookService.findBooksBySearchCriteria(searchCriteria);
		// then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		BookEntity bookEntity = books.get(0);
		assertEquals("Pierwsza książka", bookEntity.getTitle());
	}

	@Test
	public void testShouldFindBookWhenTitleAndLibraryAndAuthor() {
		// given
		BookSearchCriteria searchCriteria = new BookSearchCriteria();
		searchCriteria.setLibraryName("Politechnika Wrocławska");
		searchCriteria.setTitle("Pierwsza książka");
		searchCriteria.setAuthors("Kowalski");
		// when
		List<BookEntity> books = new ArrayList<BookEntity>();
		books = bookService.findBooksBySearchCriteria(searchCriteria);
		// then
		assertNotNull(books);
		assertFalse(books.isEmpty());
		BookEntity bookEntity = books.get(0);
		assertEquals("Pierwsza książka", bookEntity.getTitle());
	}

}
