package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.util.StringUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QLibraryEntity;
import pl.spring.demo.searchCriteriaRepository.searchCriteriaRepository;
import pl.spring.demo.searchcriteria.BookSearchCriteria;

public class BookRepositoryImpl implements searchCriteriaRepository {

	@PersistenceContext
	protected EntityManager entityManager;

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
