package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.domain.frete.calculo.*;
import br.com.itau.geradornotafiscal.domain.frete.impl.FrenteImpl;
import br.com.itau.geradornotafiscal.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class FreteTest {

    private final Map<Regiao, CalculoFrete> calculosFrete;
    public FreteTest() {
        calculosFrete = new HashMap<>();
        calculosFrete.put(Regiao.NORDESTE, new CalculoFreteRegiaoNordeste());
        calculosFrete.put(Regiao.NORTE, new CalculoFreteRegiaoNorte());
        calculosFrete.put(Regiao.SUDESTE, new CalculoFreteRegiaoSudeste());
        calculosFrete.put(Regiao.SUL, new CalculoFreteRegiaoSul());
        calculosFrete.put(Regiao.CENTRO_OESTE, new CalculoFreteRegiaoCentroOeste());
    }

    @ParameterizedTest
    @CsvSource({
            "100",
            "232",
            "1080",
            "3455",
    })
    public void givenAValidRegion_whenCallsCalcularFreteAndRegionIsNorteShouldReturnValorFreteComOitoPorcento(double valorFrete){
        //given
        final var percentualNorte = 1.08;
        final var expectedValue = valorFrete * percentualNorte;
        final var frete = new FrenteImpl(this.calculosFrete);
        var destinatario = new Destinatario();
        destinatario.setEnderecos(List.of(Endereco.builder()
                .regiao(Regiao.NORTE)
                        .finalidade(Finalidade.ENTREGA)
                .build()));
        var pedido = new Pedido();
        pedido.setValorFrete(valorFrete);
        //when
        final var valoreFretePercentual = frete.calcularFrete(destinatario, pedido);
        //then
        Assertions.assertEquals(expectedValue, valoreFretePercentual);
    }

    @ParameterizedTest
    @CsvSource({
            "100",
            "232",
            "1080",
            "3455",
    })
    public void givenAValidRegion_whenCallsCalcularFreteAndRegionIsNordesteShouldReturnValorFreteComOitoPontoCincoPorcento(double valorFrete){
        //given
        final var percentualNorte = 1.085;
        final var expectedValue = valorFrete * percentualNorte;
        final var frete = new FrenteImpl(this.calculosFrete);
        var destinatario = new Destinatario();
        destinatario.setEnderecos(List.of(Endereco.builder()
                .regiao(Regiao.NORDESTE)
                .finalidade(Finalidade.COBRANCA_ENTREGA)
                .build()));
        var pedido = new Pedido();
        pedido.setValorFrete(valorFrete);
        //when
        final var valoreFretePercentual = frete.calcularFrete(destinatario, pedido);
        //then
        Assertions.assertEquals(expectedValue, valoreFretePercentual);
    }

    @ParameterizedTest
    @CsvSource({
            "100",
            "232",
            "1080",
            "3455",
    })
    public void givenAValidRegion_whenCallsCalcularFreteAndRegionIsSudesteShouldReturnValorFreteComQutroPontoOitoPorcento(double valorFrete){
        //given
        final var percentualNorte = 1.048;
        final var expectedValue = valorFrete * percentualNorte;
        final var frete = new FrenteImpl(this.calculosFrete);
        var destionation = new Destinatario();
        destionation.setEnderecos(List.of(Endereco.builder()
                .regiao(Regiao.SUDESTE)
                .finalidade(Finalidade.COBRANCA_ENTREGA)
                .build()));
        var pedido = new Pedido();
        pedido.setValorFrete(valorFrete);
        //when
        final var valoreFretePercentual = frete.calcularFrete(destionation, pedido);
        //then
        Assertions.assertEquals(expectedValue, valoreFretePercentual);
    }

    @ParameterizedTest
    @CsvSource({
            "100",
            "232",
            "1080",
            "3455",
    })
    public void givenAValidRegion_whenCallsCalcularFreteAndRegionIsSulShouldReturnValorFreteComSeisPorcento(double valorFrete){
        //given
        final var percentualNorte = 1.06;
        final var expectedValue = valorFrete * percentualNorte;
        final var frete = new FrenteImpl(this.calculosFrete);
        var destionation = new Destinatario();
        destionation.setEnderecos(List.of(Endereco.builder()
                .regiao(Regiao.SUL)
                .finalidade(Finalidade.ENTREGA)
                .build()));
        var pedido = new Pedido();
        pedido.setValorFrete(valorFrete);
        //when
        final var valoreFretePercentual = frete.calcularFrete(destionation, pedido);
        //then
        Assertions.assertEquals(expectedValue, valoreFretePercentual);
    }

    @ParameterizedTest
    @CsvSource({
            "100",
            "232",
            "1080",
            "3455",
    })
    public void givenAValidRegion_whenCallsCalcularFreteAndRegionIsCentroOesteShouldReturnValorFreteComSetePorcento(double valorFrete){
        //given
        final var percentualNorte = 1.07;
        final var expectedValue = valorFrete * percentualNorte;
        final var frete = new FrenteImpl(this.calculosFrete);
        var destionation = new Destinatario();
        destionation.setEnderecos(List.of(Endereco.builder()
                .regiao(Regiao.CENTRO_OESTE)
                .finalidade(Finalidade.ENTREGA)
                .build()));
        var pedido = new Pedido();
        pedido.setValorFrete(valorFrete);
        //when
        final var valoreFretePercentual = frete.calcularFrete(destionation, pedido);
        //then
        Assertions.assertEquals(expectedValue, valoreFretePercentual);
    }
}
