package br.com.itau.geradornotafiscal.domain.frete.impl;

import br.com.itau.geradornotafiscal.domain.frete.Frete;
import br.com.itau.geradornotafiscal.domain.frete.calculo.CalculoFrete;
import br.com.itau.geradornotafiscal.domain.frete.utils.FreteUtils;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FrenteImpl implements Frete {
    private final Map<Regiao, CalculoFrete> calculosFrete;
    @Override
    public double calcularFrete(Destinatario destinatario, Pedido pedido) {
        var regiao  = FreteUtils.getRegionPorEntregaEFidelidade(destinatario);
        var calculoFrete = this.calculosFrete.getOrDefault(regiao, null);

        return Objects.nonNull(calculoFrete) ? calculoFrete.calcularValorFretePercentual(pedido.getValorFrete()) : 0.0;
    }
}
