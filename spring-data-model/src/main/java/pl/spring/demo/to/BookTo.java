package pl.spring.demo.to;

import java.util.List;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.LibraryEntity;

public class BookTo {
	private Long id;
	private String title;
	private List<AuthorEntity> authors;
	private LibraryEntity libraryName;

	public BookTo() {
	}

	public BookTo(Long id, String title, List<AuthorEntity> authors, LibraryEntity libraryName) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.libraryName = libraryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}

	public LibraryEntity getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(LibraryEntity libraryName) {
		this.libraryName = libraryName;
	}

}
