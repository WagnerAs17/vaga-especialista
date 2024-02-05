package br.com.itau.calculadoratributos.fisica;

import br.com.itau.geradornotafiscal.domain.exceptions.AliquotaNaoEncontradaException;
import br.com.itau.geradornotafiscal.domain.fisica.PessoaFisica;
import br.com.itau.geradornotafiscal.domain.fisica.aliquota.*;
import br.com.itau.geradornotafiscal.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PessoaFisicaTest {

    private final List<AliquotaPF> aliquotaPFS;
    public PessoaFisicaTest() {
        aliquotaPFS = List.of(
                new AliquotaPessoaFisicaDoze(),
                new AliquotaPessoaFisicaQuinze(),
                new AliquotaPessoaFisicaDezessete(),
                new AliquotaPessoaFisicaZerada()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "400,0",
            "499,0",
            "1000,0.12",
            "2000,0.12",
            "3000,0.15",
            "3500,0.15",
            "3600,0.17"
    })
    public void givenAValidValorTotalItens_whenCallsGetAliquota_shouldReturnAliquotaZerada(double valorTotalItems, double aliquotaEsperada){
        //given
        var pessoaFisica = new PessoaFisica(aliquotaPFS);
        final var pedido = new Pedido();
        pedido.setValorTotalItens(valorTotalItems);
        //when
        var aliqutota  = pessoaFisica.getAliquota(pedido);
        //then
        Assertions.assertEquals(aliquotaEsperada, aliqutota);
    }

    @Test
    public void givenAnInvalidListOfALiquotas_whenCallGetAliquota_shouldReturnException(){
        //given
        final var mensagemEsperada = "Aliquota nao encontrada para o valor total de items";
        final var semAliquotas = List.<AliquotaPF>of();
        final var pessoaFisica = new PessoaFisica(semAliquotas);
        final var valorTotalItems = 300;
        final var pedido = new Pedido();
        pedido.setValorTotalItens(valorTotalItems);
        //when
        var ex  = Assertions.assertThrows(AliquotaNaoEncontradaException.class, () -> pessoaFisica.getAliquota(pedido));
        Assertions.assertEquals(mensagemEsperada, ex.getMessage());
    }
}
