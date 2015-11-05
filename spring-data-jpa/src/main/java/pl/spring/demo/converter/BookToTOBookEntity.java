package pl.spring.demo.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookToTOBookEntity implements Converter<BookTo, BookEntity> {

	@Override
	public BookEntity convert(BookTo bookTo) {
		Long id;
		String title;
		String authors;

		id = bookTo.getId();
		title = bookTo.getTitle();

		if (bookTo.getAuthors() != null) {
			List<AthorTo> listAuthors = bookTo.getAuthors();
			authors = listAuthors.get(0).getFirstName() + " " + listAuthors.get(0).getLastName();
		} else {
			authors = null;
		}

		return new BookEntity(id, title, authors);

	}

}
