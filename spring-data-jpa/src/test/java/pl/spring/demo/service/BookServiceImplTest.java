package pl.spring.demo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import junit.framework.Assert;
import pl.spring.demo.config.AppConfiguraion;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "CommonServiceTest-context.xml")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BookServiceImplTest {

	@Configuration
	@Import(AppConfiguraion.class)
	static class ContextConfiguration {
	}

	@Autowired
	public BookService bookService;
	@Autowired
	public BookDao bookDao;

	@Test
	@Ignore
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(22L);
		// when
		bookService.saveBook(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}

	@Test
	public void testSaveBook() {
		// given
		BookEntity bookToSave = new BookEntity(null, "title", "author");
		// when
		BookEntity result = bookDao.save(bookToSave);
		// then
		Assert.assertEquals(6L, result.getId().longValue());
	}

}
