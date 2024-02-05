package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroRealPessoaJuridicaTres implements AliquotaLucroReal {

    @Override
    public double getAliquota() {
        return 0.03;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens < 1000;
    }
}
