package br.com.itau.calculadoratributos.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.*;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.LucroPresumido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LucroPresumidoTest {

    private final List<AliquotaLucroPresumido> lucrosPresumido;
    public LucroPresumidoTest(){
        this.lucrosPresumido = List.of(
                new AliquotaLucroPresumidoPessoaJuridicaDezesseis(),
                new AliquotaLucroPresumidoPessoaJuridicaNove(),
                new AliquotaLucroPresumidoPessoaJuridicaTres(),
                new AliquotaLucroPresumidoPessoaJuridicaVinte()
        );
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan2000_whenCallsGetAliquota_shouldReturnAliquotaNove(){
        //given
        final var lucroPresumido = new LucroPresumido(lucrosPresumido);
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.09;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        //when
        final var result = lucroPresumido.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroPresumido.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsGetAliquota_shouldReturnAliquotaDezesseis(){
        //given
        final var lucroPresumido = new LucroPresumido(lucrosPresumido);
        final var valorTotalItens = 4999;
        final var expectedAliquota = 0.16;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        //when
        final var result = lucroPresumido.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroPresumido.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan1000_whenCallsGetAliquota_shouldReturnAliquotaTres(){
        //given
        final var lucroPresumido = new LucroPresumido(lucrosPresumido);
        final var valorTotalItens = 999;
        final var expectedAliquota = 0.03;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        //when
        final var result = lucroPresumido.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroPresumido.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5001_whenCallsGetAliquota_shouldReturnAliquotaVinte(){
        //given
        final var lucroPresumido = new LucroPresumido(lucrosPresumido);
        final var valorTotalItens = 5002;
        final var expectedAliquota = 0.20;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        //when
        final var result = lucroPresumido.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroPresumido.getRegimeTributacao());
    }
}
