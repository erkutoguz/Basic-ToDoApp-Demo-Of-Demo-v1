import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DbManager {
	DbHelper helper = new DbHelper();
	Connection connection;

	public DbManager() {
		try {
			connection = helper.getConection();
		} catch (SQLException e) {
		}
	}

	public void add(ToDo entity) throws SQLException, MySQLIntegrityConstraintViolationException {
		String name = entity.getTitle();
		String genre = entity.getGenre().name();
		String detail = entity.getDetail();
		String sql = "INSERT INTO TODOS(name, genre, details) VALUES(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, genre);
		statement.setString(3, detail);
		statement.executeUpdate();
		statement.close();
	}

	public ArrayList<ToDo> get() throws SQLException {
		ArrayList<ToDo> arr = new ArrayList<>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM TODOS");
		while (resultSet.next()) {
			arr.add(new ToDo(resultSet.getString("name"), Genre.getGenre(resultSet.getString("genre")),
					resultSet.getString("details")));
		}
		statement.close();
		return arr;
	}

	public ToDo select(String name) throws SQLException {
		String sql = "SELECT * FROM TODOS WHERE name='" + name + "'";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.next();
		String toDoName = resultSet.getString("name");
		Genre genre = Genre.getGenre(resultSet.getString("genre"));
		String detail = resultSet.getString("details");
		statement.close();
		return new ToDo(toDoName, genre, detail);
	}

	public void delete(ToDo entity) throws SQLException {
		String sql = "DELETE FROM TODOS WHERE name='" + entity.getTitle() + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.executeUpdate();
		statement.close();
	}


}
