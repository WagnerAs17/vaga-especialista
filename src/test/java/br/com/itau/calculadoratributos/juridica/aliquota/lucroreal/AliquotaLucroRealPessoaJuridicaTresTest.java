package br.com.itau.calculadoratributos.juridica.aliquota.lucroreal;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaTres;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroRealPessoaJuridicaTresTest {

    @Test
    public void givenAValidValorTotalItensWithValueLessThan1000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaTres = new AliquotaLucroRealPessoaJuridicaTres();
        final var valorTotalItens = 999;
        final var expectedAliquota = 0.03;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTres.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals1000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaTres = new AliquotaLucroRealPessoaJuridicaTres();
        final var valorTotalItens = 1000;
        final var expectedAliquota = 0.03;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTres.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan1000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaTres = new AliquotaLucroRealPessoaJuridicaTres();
        final var valorTotalItens = 1001;
        final var expectedAliquota = 0.03;
        //when
        final var result = aliquotaTres.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
        Assertions.assertEquals(expectedAliquota, aliquotaTres.getAliquota());
    }
}
