package br.com.itau.geradornotafiscal.domain.fisica.aliquota;

import org.springframework.stereotype.Service;

@Service
public class AliquotaPessoaFisicaDezessete implements AliquotaPF {
    @Override
    public double getAliquota() {
        return 0.17;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens > 3500;
    }
}
