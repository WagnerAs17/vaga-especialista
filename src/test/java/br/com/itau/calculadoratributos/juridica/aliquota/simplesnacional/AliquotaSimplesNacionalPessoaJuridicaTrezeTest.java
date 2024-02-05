package br.com.itau.calculadoratributos.juridica.aliquota.simplesnacional;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTreze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaSimplesNacionalPessoaJuridicaTrezeTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaTreze = new AliquotaSimplesNacionalPessoaJuridicaTreze();
        final var valorTotalItens = 4999;
        final var expectedAliquota = 0.13;
        //when
        final var result = aliquotaTreze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTreze.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaTreze = new AliquotaSimplesNacionalPessoaJuridicaTreze();
        final var valorTotalItens = 5000;
        final var expectedAliquota = 0.13;
        //when
        final var result = aliquotaTreze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTreze.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaTreze = new AliquotaSimplesNacionalPessoaJuridicaTreze();
        final var valorTotalItens = 5001;
        //when
        final var result = aliquotaTreze.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
