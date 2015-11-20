package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

	@Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
	public List<BookEntity> findBookByTitle(@Param("title") String title);

	// @Query("select book from BookEntity book where exists (select author from
	// AuthorEntity author where book.id=author.id)")

	@Query("select book from BookEntity book join book.authors author where upper(author.author.firstName) like concat(upper(:name), '%') or upper(author.author.lastName) like concat(upper(:name), '%')")
	public List<BookEntity> findBookByAuthor(@Param("name") String name);

}
// (select person from PersonalDataEntity person where upper(person.firstName)
// like concat(upper(:firstName), '%')