package com.jsp.dao.usuariodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.dao.util.Conexao;
import com.jsp.modelo.Papel;
import com.jsp.modelo.Usuario;

public class PapelDAO {

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
	
	public Papel buscarPapelPorTipo(String tipo) throws SQLException {
        Papel papel = null;
        String sql = "SELECT * FROM papel WHERE tipo_papel LIKE ?";
         
        conectar();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tipo);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	long id = resultSet.getLong("id");
        	String tipoPapel = resultSet.getString("tipo_papel");

			papel = new Papel(id, tipoPapel);
		}
         
        resultSet.close();
        statement.close();
        
        desconectar();
        
        return papel;
    }

	public void atribuirPapelUsuario(Papel papel, Usuario usuario) throws SQLException{
		String sql = "INSERT INTO usuario_papel (usuario_id, papel_id)"
				+ " VALUES (?, ?)";		    
		
		conectar();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuario.getId());
		statement.setLong(2, papel.getId());
		
		statement.executeUpdate();
		statement.close();

		desconectar();
	}

	

}
