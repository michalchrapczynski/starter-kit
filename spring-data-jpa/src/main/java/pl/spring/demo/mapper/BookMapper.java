package pl.spring.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.entity.PersonalData;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.LibraryTo;

public class BookMapper {

	public static BookTo map(BookEntity bookEntity) {
		if (bookEntity != null) {
			List<AuthorTo> authors = bookEntity.getAuthors().stream().map(BookMapper::map).collect(Collectors.toList());
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors, map(bookEntity.getLibraryName()));
		}
		return null;
	}

	public static BookEntity map(BookTo bookTo) {
		if (bookTo != null) {
			List<AuthorEntity> authors = bookTo.getAuthors().stream().map(BookMapper::map).collect(Collectors.toList());
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), authors, map(bookTo.getLibraryName()));
		}
		return null;
	}

	public static LibraryTo map(LibraryEntity libraryEntity) {
		if (libraryEntity != null) {
			return new LibraryTo(libraryEntity.getId(), libraryEntity.getName());
		}
		return null;
	}

	public static LibraryEntity map(LibraryTo libraryTo) {
		if (libraryTo != null) {
			return new LibraryEntity(libraryTo.getId(), libraryTo.getName(), null);
		}
		return null;
	}

	public static AuthorTo map(AuthorEntity authorEntity) {
		if (authorEntity != null) {
			PersonalData author = authorEntity.getAuthor();
			return new AuthorTo(authorEntity.getId(), author.getFirstName(), author.getLastName());
		}
		return null;
	}

	public static AuthorEntity map(AuthorTo authorTo) {
		if (authorTo != null) {
			PersonalData author = new PersonalData(authorTo.getFirstName(), authorTo.getLastName());
			return new AuthorEntity(authorTo.getId(), author);
		}
		return null;
	}

	public static List<BookTo> map2To(List<BookEntity> bookEntities) {
		return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
	}

	public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
		return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
	}

	public static String mapAuthors(PersonalData author) {
		if (author != null) {
			return author.getFirstName() + " " + author.getLastName();
		}
		return null;
	}
}
