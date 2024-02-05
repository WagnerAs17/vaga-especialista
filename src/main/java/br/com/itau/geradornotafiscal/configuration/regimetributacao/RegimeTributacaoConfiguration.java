package br.com.itau.geradornotafiscal.configuration.regimetributacao;

import br.com.itau.geradornotafiscal.domain.frete.calculo.CalculoFrete;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.RegimeTributacao;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RegimeTributacaoConfiguration {

    private final List<RegimeTributacao> regimesTributacao;

    @Bean
    public Map<RegimeTributacaoPJ, RegimeTributacao> regimeTributacaoPJRegimeTributacaoMap(){
        Map<RegimeTributacaoPJ, RegimeTributacao> regimesMap = new EnumMap<>(RegimeTributacaoPJ.class);
        regimesTributacao.forEach(regime -> regimesMap.put(regime.getRegimeTributacao(), regime));
        return regimesMap;
    }
}
