package br.com.itau.calculadoratributos;

import br.com.itau.geradornotafiscal.domain.Pessoa;
import br.com.itau.geradornotafiscal.domain.fisica.PessoaFisica;
import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPF;
import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPessoaFisicaDoze;
import br.com.itau.geradornotafiscal.domain.frete.impl.FrenteImpl;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import br.com.itau.geradornotafiscal.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GeradorNotaFiscalServiceImplTest {

    @InjectMocks
    private GeradorNotaFiscalServiceImpl geradorNotaFiscalService;
    @Mock
    private CalculadoraAliquotaProdutoServiceImpl calculadoraAliquotaProdutoImpl;
    @Mock
    private FrenteImpl frente;
    @Mock
    private Map<TipoPessoa, Pessoa> pessoas;
    @Mock Pessoa  pessoa;
    @Mock
    private EntregaServiceImpl entregaService;
    @Mock
    private RegistroServiceImpl registroService;
    @Mock
    private FinanceiroServiceImpl financeiroService;
    @Mock
    private EstoqueServiceImpl estoqueService;
    @Mock
    private CalculadoraAliquotaProdutoServiceImpl calculadoraAliquotaProdutoService;

    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaFisicaWithValorTotalItensLessThan500() {
        //given
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(400);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);;
        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));
        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(100);
        item.setQuantidade(4);
        pedido.setItens(Arrays.asList(item));
        Mockito.when(pessoas.get(Mockito.any()))
                .thenReturn(new PessoaFisica(List.<AliquotaPF>of(
                        new AliquotaPessoaFisicaDoze()
                )));
        //when
        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        //then
        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0, notaFiscal.getItens().get(0).getValorTributoItem());
    }

    @Test
    public void shouldGenerateNotaFiscalForTipoPessoaJuridicaWithRegimeTributacaoLucroPresumidoAndValorTotalItensGreaterThan5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(6000);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(6);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.20 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
    }

}