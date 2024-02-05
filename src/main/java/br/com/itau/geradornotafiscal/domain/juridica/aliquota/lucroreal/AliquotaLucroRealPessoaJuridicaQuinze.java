package br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaLucroRealPessoaJuridicaQuinze implements AliquotaLucroReal{
    @Override
    public double getAliquota() {
        return 0.15;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 5000;
    }
}
