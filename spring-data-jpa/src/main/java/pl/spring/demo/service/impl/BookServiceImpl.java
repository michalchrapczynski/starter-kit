package pl.spring.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QLibraryEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.searchcriteria.BookSearchCriteria;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<BookTo> findAllBooks() {
		return BookMapper.map2To(bookRepository.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return BookMapper.map2To(bookRepository.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String name) {

		return BookMapper.map2To(bookRepository.findBookByAuthor(name));
	}

	@Override
	@Transactional(readOnly = false)
	public BookTo saveBook(BookTo book) {
		BookEntity entity = BookMapper.map(book);
		entity = bookRepository.save(entity);
		return BookMapper.map(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeBook(BookTo book) {
		BookEntity entity = BookMapper.map(book);
		bookRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeBookPoster(Long id) {
		bookRepository.delete(id);
	}

	@Override
	public List<BookEntity> findBooksBySearchCriteria(BookSearchCriteria searchCriteria) {
		final QBookEntity bookEntity = QBookEntity.bookEntity;
		final JPAQuery query = new JPAQuery(entityManager, HQLTemplates.DEFAULT).from(bookEntity);

		if (searchCriteria != null) {
			final BooleanBuilder predicate = new BooleanBuilder();

			if (!StringUtils.isEmpty(searchCriteria.getTitle())) {
				final String title = searchCriteria.getTitle();
				predicate.and(bookEntity.title.startsWithIgnoreCase(title));
			}
			if (!StringUtils.isEmpty(searchCriteria.getAuthors())) {
				final String author = searchCriteria.getAuthors();
				predicate.and(bookEntity.authors.any().author.firstName.startsWithIgnoreCase(author)
						.or(bookEntity.authors.any().author.lastName.startsWithIgnoreCase(author)));
			}
			if (!StringUtils.isEmpty(searchCriteria.getLibraryName())) {
				QLibraryEntity libEntity = QLibraryEntity.libraryEntity;
				predicate.and(new JPASubQuery().from(libEntity)
						.where(libEntity.books.any().bookEntity.title.eq(bookEntity.title)).exists());
			}
			query.where(predicate);
			return query.listResults(bookEntity).getResults();
		}
		return null;
	}
}
