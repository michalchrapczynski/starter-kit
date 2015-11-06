package pl.spring.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.converter.BookEntityTOBookTo;
import pl.spring.demo.converter.BookToTOBookEntity;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	private BookEntityTOBookTo bookEntityTOBookTo = new BookEntityTOBookTo();
	private BookToTOBookEntity bookToTOBookEntity = new BookToTOBookEntity();

	@Override
	public List<BookTo> findAllBooks() {
		List<BookTo> listFindALL = new ArrayList<>();

		for (BookEntity book : bookDao.findAll()) {
			listFindALL.add(bookEntityTOBookTo.convert(book));
		}
		return listFindALL;
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {

		List<BookTo> listFindBooksByTitle = new ArrayList<>();

		for (BookEntity book : bookDao.findBookByTitle(title)) {
			listFindBooksByTitle.add(bookEntityTOBookTo.convert(book));
		}
		return listFindBooksByTitle;
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {

		List<BookTo> listFindBooksByAuthor = new ArrayList<>();

		for (BookEntity book : bookDao.findBooksByAuthor(author)) {
			listFindBooksByAuthor.add(bookEntityTOBookTo.convert(book));
		}
		return listFindBooksByAuthor;
	}

	@Override
	public BookTo saveBook(BookTo book) {
		BookEntity bookEntity = bookToTOBookEntity.convert(book);
		BookEntity save = bookDao.save(bookEntity);
		BookTo bookTo = bookEntityTOBookTo.convert(save);
		return bookTo;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
