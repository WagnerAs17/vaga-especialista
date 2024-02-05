package br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaSimplesNacionalPessoaJuridicaTreze implements AliquotaSimplesNacional{
    @Override
    public double getAliquota() {
        return 0.13;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 5000;
    }
}
