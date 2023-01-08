package stopdlaodlewni.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the info database table.
 * 
 */
@Entity
@NamedQuery(name="Info.findAll", query="SELECT i FROM Info i")
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNews;

	private String author;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Lob
	private String description;

	private String photo;

	private String title;

	public Info() {
	}

	public int getIdNews() {
		return this.idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}