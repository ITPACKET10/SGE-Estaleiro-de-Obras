package sge.model;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

/**
 * Especialização de Usuario para o perfil de presidente; define as suas permissões e opções.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class Presidente extends Usuario {
    public Presidente(String nome, String apelido, String bi, String telefone, String morada,
                      String sexo, String email, String senha) {
        super(nome, apelido, bi, telefone, morada, sexo, email, senha, "Presidente");
    }
    /** Define as permissões disponibilizadas a este perfil. A redefinição nas subclasses exemplifica polimorfismo. */
    @Override public String[] getPermissoes() { return new String[]{"RELATORIOS", "UTILIZADORES", "GESTAO", "CONSULTAR"}; }
    /** Devolve as opções do menu para o perfil. As subclasses podem redefinir este comportamento. */
    @Override public String[] getMenuOpcoes() { return new String[]{"Painel de Controlo", "Relatório Completo", "Utilizadores"}; }
}
