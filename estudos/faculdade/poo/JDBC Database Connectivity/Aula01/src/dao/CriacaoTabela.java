package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriacaoTabela {
	static final String url = "jdbc:mysql://localhost/escola";
	
	public CriacaoTabela() {
String sql = "CREATE TABLE aluno(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, cpf CHAR(11), nome VARCHAR(50)";
		
		try {
			Connection conexao = DriverManager.getConnection(url, "root", "");
			PreparedStatement operacao = conexao.prepareStatement(sql);
			operacao.execute();
			
			System.out.println("Tabela criada com sucesso!");
			conexao.close();
			
		} catch (SQLException e) {
			System.out.print("Erro: " + e.getMessage());
		}
	}
}
