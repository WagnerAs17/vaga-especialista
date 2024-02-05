package br.com.itau.geradornotafiscal.domain.frete;

import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;

public interface Frete {

    double calcularFrete(Destinatario regiao, Pedido pedido);
}
