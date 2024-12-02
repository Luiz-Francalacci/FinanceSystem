package negocio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
	
	static private Connection connection = null;


	
	public static Connection getConnection() throws SQLException{
		try {
			if(connection == null || connection.isClosed()) {
				String URL = "jdbc:postgresql://localhost:5432/postgres";
				String USER = "postgres";
				String PASSWORD = "2810";
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		}catch(SQLException e) {
			System.err.println("Erro ao conectar" + e.getMessage());
		}
		return connection;
	}
	
	
}
