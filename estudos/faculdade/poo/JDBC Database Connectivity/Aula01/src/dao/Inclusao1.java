package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inclusao1 {
	static final String url = "jdbc:mysql://localhost/escola";
	
	public Inclusao1() {
		String sql = "INSERT INTO aluno(cpf, nome) VALUES('12345678910', 'Enzo Ribas')";
		
		try {
			Connection conexao = DriverManager.getConnection(url, "root", "");
			PreparedStatement operacao = conexao.prepareStatement(sql);
			operacao.execute();
			
			System.out.println("Aluno incluído com sucesso!");
			conexao.close();
			
		} catch (SQLException e) {
			System.out.print("Erro: " + e.getMessage());
		}
	}
}
