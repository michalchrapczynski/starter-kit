package pl.spring.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AthorTo;
import pl.spring.demo.to.BookTo;

public class BookEntityTOBookTo implements Converter<BookEntity, BookTo> {

	@Override
	public BookTo convert(BookEntity bookEntity) {
		Long id;
		String title;
		List<AthorTo> authors = null;
		if (bookEntity.getAuthors() != null) {
			authors = new ArrayList<>();
		}

		title = bookEntity.getTitle();
		if (bookEntity.getAuthors() != null) {
			String[] dataAuthor = bookEntity.getAuthors().trim().split(" ");
			AthorTo atgorTo = new AthorTo(0L, dataAuthor[0], dataAuthor[1]);

			authors.add(atgorTo);

		}
		if (bookEntity.getId() != null) {
			id = bookEntity.getId().longValue();
			return new BookTo(id, title, authors);
		}
		return new BookTo(null, title, authors);

	}

}
