package pl.spring.demo.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {

	@Id
	@GeneratedValue
	private long id;

	@Embedded
	private PersonalData author;

	protected AuthorEntity() {
		// for hibernate
	}

	@ManyToMany(mappedBy = "authors")
	private List<BookEntity> book;

	public AuthorEntity(long id, PersonalData author) {
		this.id = id;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonalData getAuthor() {
		return author;
	}

	public void setAuthor(PersonalData author) {
		this.author = author;
	}

}
