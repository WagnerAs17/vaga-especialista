package br.com.itau.calculadoratributos.juridica;

import br.com.itau.geradornotafiscal.domain.juridica.PessoaJuridica;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumido;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaDezesseis;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaNove;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaTres;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumidoPessoaJuridicaVinte;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroReal;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaNove;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaQuinze;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaTres;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroRealPessoaJuridicaVinte;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacional;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaDezenove;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaSete;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTres;
import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacionalPessoaJuridicaTreze;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.LucroPresumido;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.LucroReal;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.RegimeTributacao;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.SimplesNacional;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class PessoaJuridicaTest {

    private final List<AliquotaLucroPresumido> lucrosPresumido;
    private final List<AliquotaLucroReal> lucrosReal;
    private final List<AliquotaSimplesNacional> simplesNacionais;

    public PessoaJuridicaTest() {
        this.lucrosPresumido = List.of(
                new AliquotaLucroPresumidoPessoaJuridicaDezesseis(),
                new AliquotaLucroPresumidoPessoaJuridicaNove(),
                new AliquotaLucroPresumidoPessoaJuridicaTres(),
                new AliquotaLucroPresumidoPessoaJuridicaVinte()
        );

        this.lucrosReal = List.of(
                new AliquotaLucroRealPessoaJuridicaNove(),
                new AliquotaLucroRealPessoaJuridicaQuinze(),
                new AliquotaLucroRealPessoaJuridicaTres(),
                new AliquotaLucroRealPessoaJuridicaVinte()
        );

        this.simplesNacionais = List.of(
                new AliquotaSimplesNacionalPessoaJuridicaDezenove(),
                new AliquotaSimplesNacionalPessoaJuridicaSete(),
                new AliquotaSimplesNacionalPessoaJuridicaTres(),
                new AliquotaSimplesNacionalPessoaJuridicaTreze()
        );
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroPresumido_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan5000_shouldReturnAliquotaDezesseis(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroPresumido(lucrosPresumido));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 4999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.16;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroPresumido_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan2000_shouldReturnAliquotaNove(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroPresumido(lucrosPresumido));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 1999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.09;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroPresumido_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan1000_shouldReturnAliquotaTres(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroPresumido(lucrosPresumido));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.03;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroPresumido_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan5001_shouldReturnAliquotaVinte(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_PRESUMIDO;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroPresumido(lucrosPresumido));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 5002;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.20;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroReal_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan2000_shouldReturnAliquotaNove(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroReal(lucrosReal));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 1999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.09;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroReal_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan5000_shouldReturnAliquotaQuinze(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroReal(lucrosReal));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 4999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.15;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroReal_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan1000_shouldReturnAliquotaTres(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroReal(lucrosReal));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.03;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoLucroReal_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueGreatherThan5000_shouldReturnAliquotaVinte(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.LUCRO_REAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new LucroReal(lucrosReal));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 5001;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.20;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoSimplesNacional_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueGreatherThan5001_shouldReturnAliquotaDezenove(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new SimplesNacional(simplesNacionais));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 5002;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.19;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoSimplesNacional_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueGreatherThan2000_shouldReturnAliquotaSete(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new SimplesNacional(simplesNacionais));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 1999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.07;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoSimplesNacional_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan1000_shouldReturnAliquotaTres(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new SimplesNacional(simplesNacionais));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.03;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAValidPedidoWithRegimeTributacaoSimplesNacional_whenCallsGetAliquotaPedidoWithValorTotalItensWithValueLessThan5000_shouldReturnAliquotaTreze(){
        //given
        final var pedido = new Pedido();
        final var regimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new SimplesNacional(simplesNacionais));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 4999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(regimeTributacao);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.13;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }

    @Test
    public void givenAnInvalidKeyForMapRegimeTributacao_whenCallsGetAliquota_shouldReturnAliquotaZerada(){
        //given
        final var pedido = new Pedido();
        final RegimeTributacaoPJ regimeTributacao = RegimeTributacaoPJ.SIMPLES_NACIONAL;
        final var regimesTributacao = new HashMap<RegimeTributacaoPJ, RegimeTributacao>();
        regimesTributacao.put(regimeTributacao, new SimplesNacional(simplesNacionais));
        final var pessoaJuridica = new PessoaJuridica(regimesTributacao);
        final var valorTotalItens = 4999;
        final var destinatario = new Destinatario();
        destinatario.setRegimeTributacao(null);
        pedido.setValorTotalItens(valorTotalItens);
        pedido.setDestinatario(destinatario);
        final var expectedAliquota = 0.00;
        final var expectedTipoPessoa = TipoPessoa.JURIDICA;
        //when
        final var result = pessoaJuridica.getAliquota(pedido);
        //then
        Assertions.assertEquals(expectedAliquota, result);
        Assertions.assertEquals(expectedTipoPessoa, pessoaJuridica.getTipoPessoa());
    }
}
