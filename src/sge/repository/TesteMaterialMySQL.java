
package sge.repository;

import sge.model.Material;
import java.sql.SQLException;
import java.util.Scanner;

public class TesteMaterialMySQL {

    public static void main(String[] args) {

        try (Scanner entrada = new Scanner(System.in)) {

            System.out.print("Utilizador MySQL: ");
            String utilizador = entrada.nextLine();

            System.out.print("Senha MySQL: ");
            String senha = entrada.nextLine();

            MaterialRepositoryMySQL repositorio =
                new MaterialRepositoryMySQL(utilizador, senha);

            Material material = new Material(
                "Cimento Teste MySQL",
                100,
                450.00,
                "",
                "Cimentos",
                "Fornecedor Teste"
            );

            try {
                repositorio.adicionar(material);

                System.out.println(
                    "Material guardado no MySQL com sucesso!"
                );

                System.out.println(
                    "Total de materiais: " +
                    repositorio.listar().size()
                );

            } catch (SQLException e) {
                System.out.println(
                    "Erro: " + e.getMessage()
                );
            }
        }
    }
}

