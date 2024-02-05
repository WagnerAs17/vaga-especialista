package br.com.itau.geradornotafiscal.domain.juridica.tributacao;

import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;

public interface RegimeTributacao {
    RegimeTributacaoPJ getRegimeTributacao();
    double getAliquota(double valorTotalItens);
}
