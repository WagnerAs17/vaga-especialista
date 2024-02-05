package br.com.itau.geradornotafiscal.domain.fisica.aliquota;

import org.springframework.stereotype.Service;

@Service
public class AliquotaPessoaFisicaDoze implements AliquotaPF {
    @Override
    public double getAliquota() {
        return 0.12;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens <= 2000;
    }
}
