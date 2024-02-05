package br.com.itau.calculadoratributos.juridica.aliquota.simplesnacional;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaSete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaSimplesNacionalPessoaJuridicaSeteTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan2000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaSete = new AliquotaSimplesNacionalPessoaJuridicaSete();
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.07;
        //when
        final var result = aliquotaSete.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaSete.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals2000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaSete = new AliquotaSimplesNacionalPessoaJuridicaSete();
        final var valorTotalItens = 2000;
        final var expectedAliquota = 0.07;
        //when
        final var result = aliquotaSete.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaSete.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan2000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaSete = new AliquotaSimplesNacionalPessoaJuridicaSete();
        final var valorTotalItens = 2001;
        //when
        final var result = aliquotaSete.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
