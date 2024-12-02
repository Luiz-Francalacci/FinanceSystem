package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dados.Usuario;

public class UserDAO {
	
	
	public void adicionarUsuario(Usuario user) {
		String query = "INSERT INTO usuarios (login, senha) VALUES (?, ?) RETURNING id_usuario";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				user.setId(resultSet.getInt("id_usuario"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Usuario autenticarUsuario(String login, String senha) {
		String query = "SELECT * FROM usuarios WHERE login = ? AND  senha = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)){
				
				statement.setString(1, login);
				statement.setString(2, senha);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next()) {
					return new Usuario(
							resultSet.getInt("id_usuario"),
							resultSet.getString("login"),
							resultSet.getString("senha")
							);
						
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
