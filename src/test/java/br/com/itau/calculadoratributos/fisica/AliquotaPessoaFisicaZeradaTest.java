package br.com.itau.calculadoratributos.fisica;

import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPessoaFisicaZerada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaPessoaFisicaZeradaTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan500_whenCallsIsElegivel_shouldReturnTrue(){
        //given
        final var expectedAliquota = 0.0;
        final var aliquotaZerada = new AliquotaPessoaFisicaZerada();
        final var valorTotalItens = 499;
        //when
        final var result = aliquotaZerada.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaZerada.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals500_whenCallsIsElegivel_shouldReturnFalse(){
        //given
        final var aliquotaZerada = new AliquotaPessoaFisicaZerada();
        final var valorTotalItens = 500;
        //when
        final var result = aliquotaZerada.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensWithGreatherThan500_whenCallsIsElegivel_shouldReturnFalse(){
        //given
        final var aliquotaZerada = new AliquotaPessoaFisicaZerada();
        final var valorTotalItens = 501;
        //when
        final var result = aliquotaZerada.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
