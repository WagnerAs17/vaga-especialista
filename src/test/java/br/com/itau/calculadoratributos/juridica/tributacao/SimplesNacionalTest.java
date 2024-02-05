package br.com.itau.calculadoratributos.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacional;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaDezenove;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaSete;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTres;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTreze;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.SimplesNacional;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimplesNacionalTest {

    private final List<AliquotaSimplesNacional> simplesNacionais;
    public SimplesNacionalTest(){
        this.simplesNacionais = List.of(
                new AliquotaSimplesNacionalPessoaJuridicaDezenove(),
                new AliquotaSimplesNacionalPessoaJuridicaSete(),
                new AliquotaSimplesNacionalPessoaJuridicaTres(),
                new AliquotaSimplesNacionalPessoaJuridicaTreze()
        );
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5001_whenCallsGetAliquota_shouldReturnAliquotaDezenove(){
        //given
        final var lucroReal = new SimplesNacional(simplesNacionais);
        final var valorTotalItens = 5002;
        final var expectedAliquota = 0.19;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan2000_whenCallsGetAliquota_shouldReturnAliquotaSete(){
        //given
        final var lucroReal = new SimplesNacional(simplesNacionais);
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.07;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan1000_whenCallsGetAliquota_shouldReturnAliquotaTres(){
        //given
        final var lucroReal = new SimplesNacional(simplesNacionais);
        final var valorTotalItens = 999;
        final var expectedAliquota = 0.03;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsGetAliquota_shouldReturnAliquotaTreze(){
        //given
        final var lucroReal = new SimplesNacional(simplesNacionais);
        final var valorTotalItens = 4999;
        final var expectedAliquota = 0.13;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }
}
