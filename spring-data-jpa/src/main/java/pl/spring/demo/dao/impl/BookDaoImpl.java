package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> booksFindByTitle = new ArrayList<>();
		for (BookEntity bookEntity : ALL_BOOKS) {
			if (bookEntity.getTitle().contains(title)) {
				booksFindByTitle.add(bookEntity);
			}
		}
		return booksFindByTitle;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> booksFindByAuthor = new ArrayList<>();
		for (BookEntity bookEntity : ALL_BOOKS) {
			if (bookEntity.getAuthors().equalsIgnoreCase(author)) {
				booksFindByAuthor.add(bookEntity);
			}
		}
		return booksFindByAuthor;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));

	}

}
