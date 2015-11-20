package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class FindLibraryRepositoryTest {

	@Autowired
	private LibraryRepository libraryRepository;

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
