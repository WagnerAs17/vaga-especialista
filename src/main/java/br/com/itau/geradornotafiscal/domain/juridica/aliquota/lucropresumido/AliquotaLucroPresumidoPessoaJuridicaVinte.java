package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroPresumidoPessoaJuridicaVinte implements AliquotaLucroPresumido{
    @Override
    public double getAliquota() {
        return 0.20;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens >= 5001;
    }
}
