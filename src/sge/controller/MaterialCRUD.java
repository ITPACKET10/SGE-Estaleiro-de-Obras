package sge.controller;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa o contrato CRUD para materiais e grava as alterações efectuadas.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class MaterialCRUD implements Crud<Material> {
    /** Adiciona o registo e desencadeia a gravação através da camada de dados. */
    @Override public void criar(Material material) {
        DadosSistema.materiais.add(material);
        DadosSistema.salvarMateriais();
    }
    /** Devolve os registos disponíveis para consulta. */
    @Override public List<Material> listar() { return new ArrayList<>(DadosSistema.materiais); }
    /** Actualiza um registo existente, respeitando as verificações do controlador. */
    @Override public boolean atualizar(Material material) {
        if (!DadosSistema.materiais.contains(material)) return false;
        DadosSistema.salvarMateriais();
        return true;
    }
    /** Remove um registo existente e persiste a alteração quando aplicável. */
    @Override public boolean remover(Material material) {
        boolean removido = DadosSistema.materiais.remove(material);
        if (removido) DadosSistema.salvarMateriais();
        return removido;
    }
}
