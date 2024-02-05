package br.com.itau.geradornotafiscal.domain.frete.calculo;

import br.com.itau.geradornotafiscal.model.Regiao;

public interface CalculoFrete {

    Regiao getRegiao();
    double calcularValorFretePercentual(double valorFrete);
}
