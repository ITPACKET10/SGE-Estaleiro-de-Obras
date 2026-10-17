package sge.controller;

import sge.repository.Arquivo;

/** Regras de unicidade de contacto e de carteira móvel, independentes da interface. */
public final class ValidacaoTrabalhador {
    private ValidacaoTrabalhador() { }

    public static String normalizarTelemovel(String valor) {
        if (valor == null) return "";
        String digitos = valor.replaceAll("[^0-9]", "");
        if (digitos.startsWith("258") && digitos.length() == 12) digitos = digitos.substring(3);
        return digitos.matches("8[2-7]\\d{7}") ? digitos : "";
    }

    /** Verifica BI e NUIT, incluindo trabalhadores sem acesso ao sistema. */
    public static String verificarIdentificadoresUnicos(String bi, String nuit, String biIgnorado) {
        for (String linha : Arquivo.lerLinhas("trabalhadores.txt")) {
            String[] campos = linha.split(";", -1);
            if (campos.length < 16) continue;
            if (biIgnorado != null && biIgnorado.equalsIgnoreCase(campos[2].trim())) continue;
            if (bi != null && bi.trim().equalsIgnoreCase(campos[2].trim()))
                return "Já existe um trabalhador registado com este BI.";
            if (nuit != null && nuit.trim().equals(campos[15].trim()))
                return "Já existe um trabalhador registado com este NUIT.";
        }
        return null;
    }

    /**
     * @param biIgnorado BI do próprio trabalhador durante uma edição; null num novo registo.
     * @return mensagem de conflito, ou null quando os números estão disponíveis.
     */
    public static String verificarNumerosUnicos(String contacto, String pagamento,
                                                 String metodo, String biIgnorado) {
        String novoContacto = normalizarTelemovel(contacto);
        boolean movel = "M-Pesa".equals(metodo) || "e-Mola".equals(metodo);
        String novoPagamento = movel ? normalizarTelemovel(pagamento) : "";
        if (!novoContacto.isEmpty() && novoContacto.equals(novoPagamento)) {
            // O mesmo trabalhador pode usar o seu contacto para receber o salário.
            novoPagamento = "";
        }
        for (String linha : Arquivo.lerLinhas("trabalhadores.txt")) {
            String[] x = linha.split(";", -1);
            if (x.length < 4) continue;
            if (biIgnorado != null && biIgnorado.equalsIgnoreCase(x[2])) continue;
            String contactoExistente = normalizarTelemovel(x[3]);
            String pagamentoExistente = x.length > 13 && ("M-Pesa".equals(x[12]) || "e-Mola".equals(x[12]))
                    ? normalizarTelemovel(x[13]) : "";
            if (!novoContacto.isEmpty() && (novoContacto.equals(contactoExistente) || novoContacto.equals(pagamentoExistente)))
                return "Este número já está associado a outro trabalhador (contacto ou pagamento).";
            if (!novoPagamento.isEmpty() && (novoPagamento.equals(contactoExistente) || novoPagamento.equals(pagamentoExistente)))
                return "Este número de pagamento já está associado a outro trabalhador (contacto ou pagamento).";
        }
        return null;
    }
}
