
public enum Genre {
	SPORT("Sport"), HOBBY("Hobby"), SCHOOL("School"), WORK("Work"), FAMILY("Family"), FRIEND("Friend");

	String name;
	Genre(String name) {
		this.name = name;
	}

	public static Genre getGenre(String genreName) {
		return switch (genreName) {
		case "FAMILY":
			yield Genre.FAMILY;
		case "FRIEND":
			yield Genre.FRIEND;
		case "HOBBY":
			yield Genre.HOBBY;
		case "SCHOOL":
			yield Genre.SCHOOL;
		case "SPORT":
			yield Genre.SPORT;
		default:
			yield Genre.WORK;
		};
	}

}
