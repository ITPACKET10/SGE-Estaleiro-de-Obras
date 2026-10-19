
package sge.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteConexao {

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {

            System.out.print("Utilizador MySQL: ");
            String utilizador = entrada.nextLine();

            System.out.print("Senha MySQL: ");
            String senha = entrada.nextLine();

            try (Connection conexao =
                    ConexaoMySQL.conectar(utilizador, senha)) {

                System.out.println(
                    "Ligacao ao MySQL realizada com sucesso!"
                );

            } catch (SQLException e) {
                System.out.println(
                    "Erro na ligacao: " + e.getMessage()
                );
            }
        }
    }
}
