package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriacaoBD {
	static final String url = "jdbc:mysql://localhost";
	
	public CriacaoBD() {
		String sql = "CREATE DATABASE escola";
		
		try {
			Connection conexao = DriverManager.getConnection(url, "root", "");
			PreparedStatement operacao = conexao.prepareStatement(sql);
			operacao.execute();
			
			System.out.println("Banco criado com sucesso!");
			conexao.close();
		} catch (SQLException e) {
			System.out.print("Erro: " + e.getMessage());
		}
	}
}
