package br.com.itau.geradornotafiscal.domain;

public interface Aliquota {
    double getAliquota();
    boolean isElegivel(double valorTotalItens);
}
