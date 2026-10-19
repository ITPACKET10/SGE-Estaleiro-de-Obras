
package sge.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL =
        "jdbc:mysql://localhost:3306/sge_estaleiro";

    public static Connection conectar(
            String utilizador,
            String senha) throws SQLException {

        return DriverManager.getConnection(
            URL, utilizador, senha
        );
    }
}
