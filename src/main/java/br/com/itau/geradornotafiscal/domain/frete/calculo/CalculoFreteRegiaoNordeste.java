package br.com.itau.geradornotafiscal.domain.frete.calculo;

import br.com.itau.geradornotafiscal.model.Regiao;
import org.springframework.stereotype.Service;

@Service
public class CalculoFreteRegiaoNordeste implements CalculoFrete{
    @Override
    public Regiao getRegiao() {
        return Regiao.NORDESTE;
    }

    @Override
    public double calcularValorFretePercentual(double valorFrete) {
        return valorFrete * 1.085;
    }
}
