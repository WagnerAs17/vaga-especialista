package br.com.itau.geradornotafiscal.domain;

import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;

public interface Pessoa {

    TipoPessoa getTipoPessoa();

    double getAliquota(Pedido pedido);
}
