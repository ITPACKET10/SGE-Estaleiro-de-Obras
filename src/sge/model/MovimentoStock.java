package sge.model;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

/**
 * Representa uma entrada, saída ou outra movimentação de stock.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class MovimentoStock {
    private String data,material,tipo,motivo,responsavel; private int quantidade;
    public MovimentoStock(String data,String material,String tipo,int quantidade,String motivo,String responsavel){this.data=data;this.material=material;this.tipo=tipo;this.quantidade=quantidade;this.motivo=motivo;this.responsavel=responsavel;}
    public String serializar(){return data+";"+material+";"+tipo+";"+quantidade+";"+motivo.replace(";",",")+";"+responsavel;}
}
