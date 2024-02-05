package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroRealPessoaJuridicaVinte implements AliquotaLucroReal {
    @Override
    public double getAliquota() {
        return 0.20;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens >= 5000;
    }
}
