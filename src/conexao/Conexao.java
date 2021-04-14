package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao = null;

    public static Connection getConexao() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/estagio", "root", "0704");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.toString());
        }
        return (conexao);
    }
}