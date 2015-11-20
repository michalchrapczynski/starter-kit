package pl.spring.demo.searchcriteria;

public class BookSearchCriteria {

	private String title;
	private String authors;
	private String libraryName;

	public String getTitle() {
		return title;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

}
