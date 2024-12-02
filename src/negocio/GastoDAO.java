package negocio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dados.Categoria;
import dados.Gasto;

public class GastoDAO {
	
	public void addGasto(Gasto gasto) {
		String query = "INSERT INTO gastos (nome, data, descricao, valor, categoria, id_usuario) VALUES (?, ?, ?, ?, ?::categoria_enum, ?)";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, gasto.getNome());
			statement.setDate(2, java.sql.Date.valueOf(gasto.getData()));
			statement.setString(3, gasto.getDescricao());
			statement.setFloat(4, gasto.getValor());
			statement.setString(5, gasto.getCategoria().toString());
			statement.setInt(6, gasto.getIdUsuario());
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Gasto> listaPorUser(int idUsuario){
		String query = "SELECT * FROM gastos WHERE id_usuario = ?";
		List<Gasto> gastos = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, idUsuario);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				gastos.add(new Gasto(
					resultSet.getInt("id_gasto"),
					resultSet.getString("nome"),
					resultSet.getDate("data").toLocalDate(),
					resultSet.getString("descricao"),
					resultSet.getFloat("valor"),
					Categoria.valueOf(resultSet.getString("categoria")),
					resultSet.getInt("id_usuario")
				));
				
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return gastos;
	}
	
	public void removerGasto(int idusuario, String nome) {
		String query = "DELETE FROM gastos WHERE id_usuario = ? AND nome = ?";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setInt(1, idusuario);
			statement.setString(2, nome);
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarGasto(int idusuario, String nome, String opcao, String valor) {
		String query = "UPDATE gastos SET " + opcao + " = ?  WHERE id_usuario = ? AND nome = ?";
		if(opcao.equals("categoria")) {
			query = "UPDATE gastos SET " + opcao + " = ?::categoria_enum  WHERE id_usuario = ? AND nome = ?";
		}
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			if(opcao.equals("valor")) {
				statement.setFloat(1, Float.parseFloat(valor));
				
			}else if(opcao.equals("data")) {
				LocalDate da = LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				statement.setDate(1, Date.valueOf(da));
				
			}else if(opcao.equals("categoria")) {
				Categoria cat = Categoria.valueOf(valor.toUpperCase());
				statement.setString(1, cat.name());
			}else {
				statement.setString(1, valor);
				
			}
			statement.setInt(2, idusuario);
			statement.setString(3, nome);
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Gasto> filtrarPorMes(int idusuario, int mes, int ano){
		String query = "SELECT * FROM gastos WHERE id_usuario = ? AND "
				+ "EXTRACT(MONTH FROM data) = ? AND EXTRACT(YEAR FROM data) = ?";
		List<Gasto> gastos = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, idusuario);
			statement.setInt(2, mes);
			statement.setInt(3, ano);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				gastos.add(new Gasto(
						resultSet.getInt("id_gasto"),
						resultSet.getString("nome"),
						resultSet.getDate("data").toLocalDate(),
						resultSet.getString("descricao"),
						resultSet.getFloat("valor"),
						Categoria.valueOf(resultSet.getString("categoria")),
						resultSet.getInt("id_usuario")
						));
				
			
			}
				
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return gastos;
	}
	
	public List<Gasto> filtrarPorCategoria(int idusuario, Categoria categoria){
		String query = "SELECT * FROM gastos WHERE id_usuario = ? AND "
				+ "categoria = ?::categoria_enum";
		List<Gasto> gastos = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setInt(1, idusuario);
			statement.setString(2, categoria.toString());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				gastos.add(new Gasto(
						resultSet.getInt("id_gasto"),
						resultSet.getString("nome"),
						resultSet.getDate("data").toLocalDate(),
						resultSet.getString("descricao"),
						resultSet.getFloat("valor"),
						Categoria.valueOf(resultSet.getString("categoria")),
						resultSet.getInt("id_usuario")
						));
				
			
			}
				
			
			
		}catch(SQLException e) {
			System.out.println(idusuario);
			e.printStackTrace();
		}
		return gastos;
	}
	
	
	
	
	
	
	
	
	

}
