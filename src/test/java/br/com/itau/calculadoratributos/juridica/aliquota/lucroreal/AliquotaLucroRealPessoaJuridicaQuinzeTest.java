package br.com.itau.calculadoratributos.juridica.aliquota.lucroreal;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaQuinze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroRealPessoaJuridicaQuinzeTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaQuinze = new AliquotaLucroRealPessoaJuridicaQuinze();
        final var valorTotalItens = 1999;
        final var expectedAliquota = 0.15;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaQuinze.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaQuinze = new AliquotaLucroRealPessoaJuridicaQuinze();
        final var valorTotalItens = 5000;
        final var expectedAliquota = 0.15;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaQuinze.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaQuinze = new AliquotaLucroRealPessoaJuridicaQuinze();
        final var valorTotalItens = 5001;
        //when
        final var result = aliquotaQuinze.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
