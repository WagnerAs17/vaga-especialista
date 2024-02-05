package br.com.itau.geradornotafiscal.domain.fisica.aliquota;

import org.springframework.stereotype.Service;

@Service
public class AliquotaPessoaFisicaQuinze implements AliquotaPF {
    @Override
    public double getAliquota() {
        return 0.15;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 3500;
    }
}
