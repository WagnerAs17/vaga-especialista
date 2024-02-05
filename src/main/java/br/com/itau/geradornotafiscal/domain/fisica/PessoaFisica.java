package br.com.itau.geradornotafiscal.domain.fisica;

import br.com.itau.geradornotafiscal.domain.Pessoa;
import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPF;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import br.com.itau.geradornotafiscal.utils.AliquotaUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PessoaFisica implements Pessoa {

    private final List<AliquotaPF> aliquotasPessoaFisica;

    public PessoaFisica(final List<AliquotaPF> aliquotasPessoaFisica) {
        Objects.requireNonNull(aliquotasPessoaFisica);
        this.aliquotasPessoaFisica = AliquotaUtils.sorted(aliquotasPessoaFisica);
    }

    @Override
    public TipoPessoa getTipoPessoa() {
        return TipoPessoa.FISICA;
    }

    @Override
    public double getAliquota(Pedido pedido) {
        final var valorTotalItens = pedido.getValorTotalItens();
        return AliquotaUtils.getAliquotaFromAliquotas(aliquotasPessoaFisica, valorTotalItens);
    }
}
