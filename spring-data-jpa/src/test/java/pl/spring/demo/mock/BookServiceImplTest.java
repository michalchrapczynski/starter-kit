package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.converter.BookEntityTOBookTo;
import pl.spring.demo.converter.BookToTOBookEntity;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;

	private BookEntityTOBookTo bookEntityTOBookTo = new BookEntityTOBookTo();
	private BookToTOBookEntity bookToTOBookEntity = new BookToTOBookEntity();

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookEntity book = new BookEntity(null, "title", "name surname");
		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", "name surname"));
		BookTo bookTo = bookEntityTOBookTo.convert(book);
		// when
		BookTo result = bookService.saveBook(bookTo);
		// then
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}

	@Test
	public void testShouldSaveBook2() {
		// given
		BookEntity book = new BookEntity(null, "title", null);
		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", null));
		BookTo bookTo = bookEntityTOBookTo.convert(book);
		// when
		BookTo result = bookService.saveBook(bookTo);
		// then
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}

	@Test
	public void testShouldSaveBook3() {
		// given
		BookEntity book = new BookEntity(null, null, "name surname");
		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, null, "name surname"));
		BookTo bookTo = bookEntityTOBookTo.convert(book);
		// when
		BookTo result = bookService.saveBook(bookTo);
		// then
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}
}
