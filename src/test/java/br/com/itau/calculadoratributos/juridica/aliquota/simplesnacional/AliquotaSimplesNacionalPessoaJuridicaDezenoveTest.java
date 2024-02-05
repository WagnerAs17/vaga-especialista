package br.com.itau.calculadoratributos.juridica.aliquota.simplesnacional;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaDezenove;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaSimplesNacionalPessoaJuridicaDezenoveTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5001_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaDezenove = new AliquotaSimplesNacionalPessoaJuridicaDezenove();
        final var valorTotalItens = 5000;
        //when
        final var result = aliquotaDezenove.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }


    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5001_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaDezenove = new AliquotaSimplesNacionalPessoaJuridicaDezenove();
        final var valorTotalItens = 5001;
        final var expectedAliquota = 0.19;
        //when
        final var result = aliquotaDezenove.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota,aliquotaDezenove.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5001_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaDezenove = new AliquotaSimplesNacionalPessoaJuridicaDezenove();
        final var valorTotalItens = 5001;
        final var expectedAliquota = 0.19;
        //when
        final var result = aliquotaDezenove.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota,aliquotaDezenove.getAliquota());
    }
}
