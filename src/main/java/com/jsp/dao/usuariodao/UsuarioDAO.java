package com.jsp.dao.usuariodao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jsp.dao.util.Conexao;
import com.jsp.modelo.Usuario;

public class UsuarioDAO {

	private Connection connection;
	
	private void conectar() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = Conexao.getConexao();
		}
	}

	private void desconectar() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public Usuario inserirUsuario(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, email, password, login, ativo)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";		    
		
		conectar();

		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getCpf());
		Date nascimento = new Date(usuario.getDataNascimento().getTime());
		statement.setDate(3, nascimento);
		statement.setString(4, usuario.getEmail());
		statement.setString(5, usuario.getPassword());
		statement.setString(6, usuario.getLogin());
		statement.setBoolean(7, usuario.isAtivo());
		
		statement.executeUpdate();
        

		ResultSet resultSet = statement.getGeneratedKeys();
		long id = 0;
		if(resultSet.next())
			id = resultSet.getInt("id");
		statement.close();

		desconectar();
		
		usuario.setId(id);
		return usuario;

	}
	

	public List<Usuario> listarTodosUsuarios() throws SQLException {
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario";

		conectar();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date nascimento = new Date(resultSet.getDate("data_nascimento").getTime());
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String login = resultSet.getString("login");
			boolean ativo = resultSet.getBoolean("ativo");

			Usuario usuario = new Usuario(nome, cpf, nascimento, email, password, login, ativo);
			usuario.setId(id);
			listaUsuarios.add(usuario);
		}
		resultSet.close();
		statement.close();

		desconectar();

		return listaUsuarios;
	}
	
	public boolean apagarUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario where id = ?";
        
        conectar();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, usuario.getId());
         
        boolean linhaApagada = statement.executeUpdate() > 0;
        statement.close();
        
        desconectar();
        
        return linhaApagada;     
   }


	
	
	

}
