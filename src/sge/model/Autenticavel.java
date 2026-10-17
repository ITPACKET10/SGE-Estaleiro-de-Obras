package sge.model;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

/**
 * Contrato de autenticação implementado pelos utilizadores que podem iniciar sessão.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public interface Autenticavel {
    boolean autenticar(String email, String senha);
}
