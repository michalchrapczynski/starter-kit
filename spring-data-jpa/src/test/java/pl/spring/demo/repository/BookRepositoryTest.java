package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testShouldFindBookById() {
		// given
		final long bookId = 1;
		// when
		BookEntity bookEntity = bookRepository.findOne(bookId);
		// then
		assertNotNull(bookEntity);
		assertEquals("Pierwsza książka", bookEntity.getTitle());
	}

	@Test
	public void testShouldFindBooksByTitle() {
		// given
		final String bookTitle = "p";
		// when
		List<BookEntity> booksEntity = bookRepository.findBookByTitle(bookTitle);
		// then
		assertNotNull(booksEntity);
		assertFalse(booksEntity.isEmpty());
		assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
	}

	@Test
	public void testShouldFindBooksByFirstNameAuthor() {
		// given
		final String bookAuthorName = "Jan";
		// when
		List<BookEntity> booksEntity = bookRepository.findBookByAuthor(bookAuthorName);
		// then
		assertNotNull(booksEntity);
		assertFalse(booksEntity.isEmpty());
		assertEquals(bookAuthorName, booksEntity.get(0).getAuthors().get(0).getAuthor().getFirstName());
	}

	@Test
	public void testShouldFindBooksByLastNameAuthor() {
		// given
		final String bookAuthorName = "Kowalski";
		// when
		List<BookEntity> booksEntity = bookRepository.findBookByAuthor(bookAuthorName);
		// then
		assertNotNull(booksEntity);
		assertFalse(booksEntity.isEmpty());
		assertEquals(bookAuthorName, booksEntity.get(0).getAuthors().get(0).getAuthor().getLastName());
	}

}
