package pl.spring.demo.author;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuthorEntity {

	@Id
	@GeneratedValue
	private long id;

	@Embedded
	private AuthorEntity author;

}
