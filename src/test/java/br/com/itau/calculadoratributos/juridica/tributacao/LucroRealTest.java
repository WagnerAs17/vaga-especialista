package br.com.itau.calculadoratributos.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroReal;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaNove;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaQuinze;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaTres;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaVinte;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.LucroReal;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LucroRealTest {

    private final List<AliquotaLucroReal> lucrosReal;
    public LucroRealTest(){
        this.lucrosReal = List.of(
                new AliquotaLucroRealPessoaJuridicaNove(),
                new AliquotaLucroRealPessoaJuridicaQuinze(),
                new AliquotaLucroRealPessoaJuridicaTres(),
                new AliquotaLucroRealPessoaJuridicaVinte()
        );
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan2000_whenCallsGetAliquota_shouldReturnAliquotaNove(){
        //given
        final var lucroReal = new LucroReal(lucrosReal);
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.09;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsGetAliquota_shouldReturnAliquotaQuinze(){
        //given
        final var lucroReal = new LucroReal(lucrosReal);
        final var valorTotalItens = 4999;
        final var expectedAliquota = 0.15;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan1000_whenCallsGetAliquota_shouldReturnAliquotaTres(){
        //given
        final var lucroReal = new LucroReal(lucrosReal);
        final var valorTotalItens = 999;
        final var expectedAliquota = 0.03;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5000_whenCallsGetAliquota_shouldReturnAliquotaVinte(){
        //given
        final var lucroReal = new LucroReal(lucrosReal);
        final var valorTotalItens = 5002;
        final var expectedAliquota = 0.20;
        final var expectedRegimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        //when
        final var result = lucroReal.getAliquota(valorTotalItens);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedRegimeTributacao, lucroReal.getRegimeTributacao());
    }
}
