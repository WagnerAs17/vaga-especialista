package br.com.itau.geradornotafiscal.domain.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucropresumido.AliquotaLucroPresumido;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.utils.AliquotaUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class LucroPresumido implements RegimeTributacao{

    private final List<AliquotaLucroPresumido> aliquotasLucroPresumido;

    public LucroPresumido(final List<AliquotaLucroPresumido> aliquotasLucroPresumido) {
        Objects.requireNonNull(aliquotasLucroPresumido);
        this.aliquotasLucroPresumido = AliquotaUtils.sorted(aliquotasLucroPresumido);
    }

    @Override
    public RegimeTributacaoPJ getRegimeTributacao() {
        return RegimeTributacaoPJ.LUCRO_PRESUMIDO;
    }

    @Override
    public double getAliquota(double valorTotalItens) {
        return AliquotaUtils.getAliquotaFromAliquotas(aliquotasLucroPresumido, valorTotalItens);
    }
}
