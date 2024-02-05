package br.com.itau.calculadoratributos.fisica;

import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPessoaFisicaQuinze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaPessoaFisicaQuinzeTest {

    @Test
    public void givenAValidaValorTotalItensWithValueLessThan3500_whenCallsIsElegivel_shouldRuturnTrue(){
        //given
        final var aliquotaQuinze = new AliquotaPessoaFisicaQuinze();
        final var valorTotalItens = 3499;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void givenAValidaValorTotalItensWithValueEquals3500_whenCallsIsElegivel_shouldRuturnTrue(){
        //given
        final var expectedAliquota = 0.15;
        final var aliquotaQuinze = new AliquotaPessoaFisicaQuinze();
        final var valorTotalItens = 3500;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaQuinze.getAliquota());
    }

    @Test
    public void givenAValidaValorTotalItensWithGreatherThan3500_whenCallsIsElegivel_shouldRuturnFalse(){
        //given
        final var expectedAliquota = 0.15;
        final var aliquotaQuinze = new AliquotaPessoaFisicaQuinze();
        final var valorTotalItens = 3501;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
        Assertions.assertEquals(expectedAliquota, aliquotaQuinze.getAliquota());
    }
}
