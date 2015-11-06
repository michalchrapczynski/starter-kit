package pl.spring.demo.to;

import java.util.List;

public class BookTo implements IdAware {
	private Long id;
	private String title;
	private List<AthorTo> authors;

	public BookTo() {
		// Empty
	}

	public BookTo(Long id, String title, List<AthorTo> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
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

	public List<AthorTo> getAuthors() {
		return authors;
	}

}
