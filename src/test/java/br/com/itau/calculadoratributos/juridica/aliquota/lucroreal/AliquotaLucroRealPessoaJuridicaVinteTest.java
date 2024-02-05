package br.com.itau.calculadoratributos.juridica.aliquota.lucroreal;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaVinte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroRealPessoaJuridicaVinteTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaVinte = new AliquotaLucroRealPessoaJuridicaVinte();
        final var valorTotalItens = 1000;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaVinte = new AliquotaLucroRealPessoaJuridicaVinte();
        final var valorTotalItens = 5001;
        final var expectedAliquota = 0.20;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaVinte.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaVinte = new AliquotaLucroRealPessoaJuridicaVinte();
        final var valorTotalItens = 5000;
        final var expectedAliquota = 0.20;
        //when
        final var result = aliquotaVinte.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaVinte.getAliquota());
    }
}
