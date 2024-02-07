package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.domain.Pessoa;
import br.com.itau.geradornotafiscal.domain.frete.Frete;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import br.com.itau.geradornotafiscal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {

    private final Frete frete;
    private final Map<TipoPessoa, Pessoa> pessoas;
    private List<ItemNotaFiscal> itensNotafiscal;

    private final EntregaService entregaService;
    private final RegistroService registroService;
    private final FinanceiroService financeiroService;
    private final EstoqueService estoqueService;
    private final CalculadoraAliquotaProdutoService calculadoraAliquotaProdutoService;

    @Override
    public NotaFiscal gerarNotaFiscal(final Pedido pedido) {
        final var destinatario = pedido.getDestinatario();
        final var aliquota = this.pessoas.get(destinatario.getTipoPessoa()).getAliquota(pedido);

        this.itensNotafiscal = calculadoraAliquotaProdutoService.calcularAliquota(pedido.getItens(), aliquota);
        final var valorFreteComPercentual = this.frete.calcularFrete(destinatario, pedido);
        final var notaFiscal = NotaFiscal.factory(pedido.getValorTotalItens(), valorFreteComPercentual, itensNotafiscal, pedido);
        notaFiscal.validarNota();
        this.estoqueService.enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        this.registroService.registrarNotaFiscal(notaFiscal);
        this.entregaService.agendarEntrega(notaFiscal);
        this.financeiroService.enviarNotaFiscalParaContasReceber(notaFiscal);

        return notaFiscal;
    }
}