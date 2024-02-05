package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroPresumidoPessoaJuridicaNove implements AliquotaLucroPresumido{
    @Override
    public double getAliquota() {
        return 0.09;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 2000;
    }
}
