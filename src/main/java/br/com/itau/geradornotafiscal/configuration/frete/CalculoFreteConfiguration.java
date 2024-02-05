package br.com.itau.geradornotafiscal.configuration.frete;

import br.com.itau.geradornotafiscal.domain.frete.calculo.CalculoFrete;
import br.com.itau.geradornotafiscal.model.Regiao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CalculoFreteConfiguration {

    private final List<CalculoFrete> calculosFrete;

    @Bean
    public Map<Regiao, CalculoFrete> calculeAliquotaPessoa(){
        Map<Regiao, CalculoFrete> calculosMap = new EnumMap<>(Regiao.class);
        calculosFrete.forEach(calculo -> calculosMap.put(calculo.getRegiao(), calculo));
        return calculosMap;
    }
}
