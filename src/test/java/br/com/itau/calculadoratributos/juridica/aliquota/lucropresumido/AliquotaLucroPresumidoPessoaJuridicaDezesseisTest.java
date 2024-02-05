package br.com.itau.calculadoratributos.juridica.aliquota.lucropresumido;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaDezesseis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AliquotaLucroPresumidoPessoaJuridicaDezesseisTest {

    @Test
    public void givenAValidValorTotalItensWithValueGreatherThan5000_whenCallsIsElegivel_ShouldReturnFalse(){
        //given
        final var aliquotaDezesseis = new AliquotaLucroPresumidoPessoaJuridicaDezesseis();
        final var valorTotalItens = 5001;
        //when
        final var result = aliquotaDezesseis.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensWithValueLessThan5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaDezesseis = new AliquotaLucroPresumidoPessoaJuridicaDezesseis();
        final var valorTotalItens = 4999;
        final var expectedAliquota = 0.16;
        //when
        final var result = aliquotaDezesseis.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaDezesseis.getAliquota());
    }

    @Test
    public void givenAValidValorTotalItensWithValueEquals5000_whenCallsIsElegivel_ShouldReturnTrue(){
        //given
        final var aliquotaDezesseis = new AliquotaLucroPresumidoPessoaJuridicaDezesseis();
        final var valorTotalItens = 5000;
        final var expectedAliquota = 0.16;
        //when
        final var result = aliquotaDezesseis.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedAliquota, aliquotaDezesseis.getAliquota());
    }
}
