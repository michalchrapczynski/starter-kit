package pl.spring.demo.library;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import pl.spring.demo.entity.BookEntity;

@Entity
public class LibraryEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "Library_name", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "books")
	private List<BookEntity> books;
}
