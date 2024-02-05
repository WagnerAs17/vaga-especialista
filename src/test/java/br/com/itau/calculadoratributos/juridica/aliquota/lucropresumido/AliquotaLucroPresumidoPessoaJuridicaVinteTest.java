package br.com.itau.calculadoratributos.juridica.aliquota.lucropresumido;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaVinte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroPresumidoPessoaJuridicaVinteTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5001_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaVinte = new AliquotaLucroPresumidoPessoaJuridicaVinte();
        final var valorTotalItens = 5000;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5001_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaVinte = new AliquotaLucroPresumidoPessoaJuridicaVinte();
        final var valorTotalItens = 5002;
        final var expectedAliquota = 0.20;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaVinte.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5001_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaVinte = new AliquotaLucroPresumidoPessoaJuridicaVinte();
        final var valorTotalItens = 5001;
        final var expectedAliquota = 0.20;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaVinte.getAliquota());
    }
}
