package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.PersonalData;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(bookService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	@Ignore
	public void testShouldCallBookService() throws Exception {
		// given
		final String bookTitle = "testTitle";

		PersonalData p1 = new PersonalData("Adam", "Nowak");
		PersonalData p2 = new PersonalData("Janusz", "Kowalski");
		AuthorEntity perosn1 = new AuthorEntity(1L, p1);
		AuthorEntity perosn2 = new AuthorEntity(2L, p2);
		List<AuthorEntity> author1 = new ArrayList<>();
		List<AuthorEntity> author2 = new ArrayList<>();

		author1.add(perosn1);
		author2.add(perosn2);

		final BookTo bookTo1 = new BookTo(1L, bookTitle, author1, null);
		final BookTo bookTo2 = new BookTo(2L, bookTitle, author2, null);

		Mockito.when(bookService.findBooksByTitle(bookTitle)).thenReturn(Arrays.asList(bookTo1, bookTo2));

		// when
		ResultActions response = this.mockMvc.perform(get("/books-by-title?titlePrefix=" + bookTitle)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		Mockito.verify(bookService).findBooksByTitle(bookTitle);

		response.andExpect(status().isOk())

				.andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
				.andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
				.andExpect(jsonPath("[0].authors").value(bookTo1.getAuthors()))
				.andExpect(jsonPath("[0].authors").value(bookTo1.getLibraryName()))

				.andExpect(jsonPath("[1].id").value(bookTo2.getId().intValue()))
				.andExpect(jsonPath("[1].title").value(bookTo2.getTitle()))
				.andExpect(jsonPath("[1].authors").value(bookTo2.getAuthors()))
				.andExpect(jsonPath("[1].authors").value(bookTo2.getAuthors()));
	}

	@Test
	public void testShouldSaveBook() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/bookToSave.json");
		String json = FileUtils.readFileToString(file);
		// when
		ResultActions response = this.mockMvc.perform(post("/book").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(status().isOk());
	}

	@Test
	public void testShouldRemoveBook() throws Exception {
		// given
		Long id = 1L;
		// when
		ResultActions response = this.mockMvc.perform(delete("/book").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(id.toString()));
		// then
		response.andExpect(status().isOk());
		Mockito.verify(bookService).removeBookPoster(id);
	}

}
