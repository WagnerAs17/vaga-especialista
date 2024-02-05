package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroRealPessoaJuridicaNove implements AliquotaLucroReal {
    @Override
    public double getAliquota() {
        return 0.09;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 2000;
    }
}
