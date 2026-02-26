package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inclusao2 {
	static final String url = "jdbc:mysql://localhost/escola";
	
	public Inclusao2() {
		String sql = "INSERT INTO aluno(cpf, nome) VALUES('11111111111', 'Enzo Ribas')," +
														"('22222222222', 'Felipe Rangel')" +
														"('33333333333', 'Natan Braslavsky')" +
														"('44444444444', 'Guilherme Ornellas')";
														
		
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
