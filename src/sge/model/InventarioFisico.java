package sge.model;

import sge.model.*;
import sge.controller.*;
import sge.view.*;
import sge.repository.*;

/**
 * Representa a conferência física das existências em stock.
 *
 * <p>Projecto académico SGE: documentação das responsabilidades desta classe.</p>
 */
public class InventarioFisico {
    private String data;
    private String material;
    private int stockSistema;
    private int quantidadeContada;
    private int diferenca;
    private String observacao;
    private String responsavel;

    public InventarioFisico(String data, String material, int stockSistema, int quantidadeContada,
                            String observacao, String responsavel) {
        this.data = data;
        this.material = material;
        this.stockSistema = stockSistema;
        this.quantidadeContada = quantidadeContada;
        this.diferenca = quantidadeContada - stockSistema;
        this.observacao = observacao == null ? "" : observacao;
        this.responsavel = responsavel;
    }

    public int getDiferenca() { return diferenca; }

    public String serializar() {
        return data + ";" + material + ";" + stockSistema + ";" + quantidadeContada + ";" + diferenca + ";" +
                observacao.replace(";", ",") + ";" + responsavel;
    }
}
