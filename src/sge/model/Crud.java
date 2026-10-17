package sge.model;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

import java.util.List;

/**
 * Interface genérica com as operações de criar, listar, actualizar e remover registos.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public interface Crud<T> {
    void criar(T item);
    List<T> listar();
    boolean atualizar(T item);
    boolean remover(T item);
}
