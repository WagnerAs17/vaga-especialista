package br.com.itau.geradornotafiscal.domain.fisica.aliquota;

import org.springframework.stereotype.Service;

@Service
public class AliquotaPessoaFisicaZerada implements AliquotaPF {
    @Override
    public double getAliquota() {
        return 0;
    }

    @Override
    public boolean isElegivel(double valorTotalItens) {
        return valorTotalItens < 500;
    }
}
