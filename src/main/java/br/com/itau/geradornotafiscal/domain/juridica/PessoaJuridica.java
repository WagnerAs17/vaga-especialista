package br.com.itau.geradornotafiscal.domain.juridica;

import br.com.itau.geradornotafiscal.domain.Pessoa;
import br.com.itau.geradornotafiscal.domain.juridica.tributacao.RegimeTributacao;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PessoaJuridica implements Pessoa {

    private final Map<RegimeTributacaoPJ, RegimeTributacao> regimeTributacoes;
    @Override
    public TipoPessoa getTipoPessoa() {
        return TipoPessoa.JURIDICA;
    }

    @Override
    public double getAliquota(Pedido pedido) {
        final var valorTotalItens = pedido.getValorTotalItens();
        final var regimeTributacao = pedido.getDestinatario().getRegimeTributacao();
        final var tributacao = this.regimeTributacoes.getOrDefault(regimeTributacao, null);
        return Objects.nonNull(tributacao) ? tributacao.getAliquota(valorTotalItens) : 0.0;
    }
}
