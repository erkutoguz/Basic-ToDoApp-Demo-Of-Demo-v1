import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	private String dbUrl = "jdbc:mysql://localhost:3306/todoDB?useSSL=false";
	private String username = "***************";
	private String password = "***************";

	public Connection getConection() throws SQLException {
		return DriverManager.getConnection(dbUrl, username, password);
	}
}
