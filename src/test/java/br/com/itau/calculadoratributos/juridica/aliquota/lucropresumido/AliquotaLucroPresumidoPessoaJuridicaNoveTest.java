package br.com.itau.calculadoratributos.juridica.aliquota.lucropresumido;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaNove;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroPresumidoPessoaJuridicaNoveTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan2000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaNove = new AliquotaLucroPresumidoPessoaJuridicaNove();
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.09;
        //when
        final var result = aliquotaNove.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaNove.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals2000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaNove = new AliquotaLucroPresumidoPessoaJuridicaNove();
        final var valorTotalItens = 2000;
        final var expectedAliquota = 0.09;
        //when
        final var result = aliquotaNove.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaNove.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan2000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaNove = new AliquotaLucroPresumidoPessoaJuridicaNove();
        final var valorTotalItens = 2001;
        //when
        final var result = aliquotaNove.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
