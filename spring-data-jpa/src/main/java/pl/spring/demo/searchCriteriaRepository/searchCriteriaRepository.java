package pl.spring.demo.searchCriteriaRepository;

import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.searchcriteria.BookSearchCriteria;

public interface searchCriteriaRepository {
	public List<BookEntity> findBooksBySearchCriteria(BookSearchCriteria searchCriteria);
}
