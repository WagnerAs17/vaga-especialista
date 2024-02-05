package br.com.itau.calculadoratributos.juridica.aliquota.simplesnacional;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTres;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaSimplesNacionalPessoaJuridicaTresTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan1000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaTres = new AliquotaSimplesNacionalPessoaJuridicaTres();
        final var valorTotalItens = 999;
        final var expectedAliquota = 0.03;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTres.getAliquota());
    }


    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan1000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaTres = new AliquotaSimplesNacionalPessoaJuridicaTres();
        final var valorTotalItens = 1001;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals1000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaTres = new AliquotaSimplesNacionalPessoaJuridicaTres();
        final var valorTotalItens = 1000;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
