package br.com.itau.geradornotafiscal.configuration.pessoa;

import br.com.itau.geradornotafiscal.domain.Pessoa;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PessoaConfiguration {

    private final List<Pessoa> pessoas;


    @Bean
    public Map<TipoPessoa, Pessoa> tipoPessoaPessoaMap(){
        final var pessoasMap = new EnumMap<TipoPessoa, Pessoa>(TipoPessoa.class);
        pessoas.forEach(pessoa -> pessoasMap.put(pessoa.getTipoPessoa(), pessoa));
        return pessoasMap;
    }
}
