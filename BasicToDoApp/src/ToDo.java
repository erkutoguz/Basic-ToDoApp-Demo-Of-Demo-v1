
public class ToDo implements IEntity {
	private String title;
	private Genre genre;
	private String detail;

	public ToDo(String title, Genre genre, String detail) {
		this.title = title;
		this.detail = detail;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
