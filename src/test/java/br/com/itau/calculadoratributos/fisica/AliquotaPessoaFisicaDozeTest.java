package br.com.itau.calculadoratributos.fisica;

import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPessoaFisicaDoze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaPessoaFisicaDozeTest {

    @Test
    public void givenAValidValorTotalItensLessThan2000_whenCallsIsEleggivel_shouldReturnTrue(){
        //given
        final var aliquotaDoze = new AliquotaPessoaFisicaDoze();
        final var valorTotalItens = 1999;
        //when
        final var result = aliquotaDoze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void givenAValidValorTotalItensEquals2000_whenCallsIsEleggivel_shouldReturnTrue(){
        //given
        final var aliquotaDoze = new AliquotaPessoaFisicaDoze();
        final var valorTotalItens = 1999;
        //when
        final var result = aliquotaDoze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void givenAValidValorTotalItensGreatherThan2000_whenCallsIsEleggivel_shouldReturnFalse(){
        //given
        final var aliquotaDoze = new AliquotaPessoaFisicaDoze();
        final var valorTotalItens = 2001;
        //when
        final var result = aliquotaDoze.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
