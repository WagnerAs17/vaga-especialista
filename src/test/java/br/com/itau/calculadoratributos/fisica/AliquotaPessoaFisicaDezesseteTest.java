package br.com.itau.calculadoratributos.fisica;

import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPessoaFisicaDezessete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AliquotaPessoaFisicaDezesseteTest {


    @Test
    public void givenAValidValorTotalItensLessThan3500_whenCallsIsElegivel_shouldReturnFalse(){
        //given
        final var aliquotaDezessete = new AliquotaPessoaFisicaDezessete();
        final var valorTotalItens = 3499;
        //when
        final var result = aliquotaDezessete.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void givenAValidValorTotalItensGreatherThan3500_whenCallsIsElegivel_shouldReturnTrue(){
        //given
        final var aliquotaDezessete = new AliquotaPessoaFisicaDezessete();
        final var valorTotalItens = 3501;
        //when
        final var result = aliquotaDezessete.isElegivel(valorTotalItens);
        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void givenAValidValorTotalItensEquals3500_whenCallsIsElegivel_shouldReturnFalse(){
        //given
        final var aliquotaDezessete = new AliquotaPessoaFisicaDezessete();
        final var valorTotalItens = 3500;
        //when
        final var result = aliquotaDezessete.isElegivel(valorTotalItens);
        //then
        Assertions.assertFalse(result);
    }
}
