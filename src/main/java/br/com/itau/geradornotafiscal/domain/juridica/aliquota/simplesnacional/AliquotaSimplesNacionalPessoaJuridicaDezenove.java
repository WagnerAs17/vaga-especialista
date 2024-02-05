package br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional;

import org.springframework.stereotype.Service;

@Service()
public class AliquotaSimplesNacionalPessoaJuridicaDezenove implements AliquotaSimplesNacional{
    @Override
    public double getAliquota() {
        return 0.19;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens >= 5001;
    }
}
