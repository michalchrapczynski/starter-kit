package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.core.Context;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
// @Sql(scripts = { "classpath:test_data.sql" }, executionPhase =
// Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// @Sql(scripts = { "classpath:drop_database.sql" }, executionPhase =
// Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LibraryRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private ApplicationContext applicationContext;
	protected Context context;

	// Musialem wrocic do startu testow poprzez stawianie kontektu, poniewaz
	// testy BookServiceImplTest.java zaciagały pusty input.sql

	@Before(value = "")
	public void setUp() {
		context = applicationContext.getBean(Context.class);
	}

	@Test
	public void testRemoveAllLibrary() {
		// given
		List<LibraryEntity> lib = libraryRepository.findAll();
		assertFalse(lib.isEmpty());
		assertEquals(2, lib.size());
		List<BookEntity> booksEntity = bookRepository.findAll();
		assertFalse(booksEntity.isEmpty());
		assertEquals(3, booksEntity.size());

		// when
		libraryRepository.delete(lib);

		// then
		lib = libraryRepository.findAll();
		assertTrue(lib.isEmpty());
		booksEntity = bookRepository.findAll();
		assertTrue(booksEntity.isEmpty());

	}

	@Test
	public void testShouldFindLibraryByName() {
		// given
		final String prefix = "poli";
		// when
		List<LibraryEntity> lib = libraryRepository.findLibraryByName(prefix);
		// then
		assertFalse(lib.isEmpty());
		assertEquals(1, lib.size());
		assertEquals("Politechnika Wrocławska", lib.get(0).getName());
	}

	@Test
	public void testShouldFindLibraryByNameNotExist() {
		// given
		final String prefix = "xxx";
		// when
		List<LibraryEntity> lib = libraryRepository.findLibraryByName(prefix);
		// then
		assertTrue(lib.isEmpty());
		assertEquals(0, lib.size());
	}

}
