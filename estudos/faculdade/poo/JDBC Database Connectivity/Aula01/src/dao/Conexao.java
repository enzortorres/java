package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	static final String url = "jdbc:mysql://localhost";
	
	public Conexao() {		
		try {		
			Connection conexao = DriverManager.getConnection(url, "root", "");
			System.out.print("Conexão realizada com sucesso");
			conexao.close();
		} catch (SQLException e) {
			System.out.print("Erro: " + e.getMessage());
		}
	}
}
