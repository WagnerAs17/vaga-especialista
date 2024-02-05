package br.com.itau.geradornotafiscal.domain.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.simplesnacional.AliquotaSimplesNacional;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.utils.AliquotaUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SimplesNacional implements RegimeTributacao{

    private final List<AliquotaSimplesNacional> aliquotasSimplesNacional;

    public SimplesNacional(final List<AliquotaSimplesNacional> aliquotasSimplesNacional) {
        Objects.requireNonNull(aliquotasSimplesNacional);
        this.aliquotasSimplesNacional = AliquotaUtils.sorted(aliquotasSimplesNacional);
    }

    @Override
    public RegimeTributacaoPJ getRegimeTributacao() {
        return RegimeTributacaoPJ.SIMPLES_NACIONAL;
    }

    @Override
    public double getAliquota(double valorTotalItens) {
        return AliquotaUtils.getAliquotaFromAliquotas(aliquotasSimplesNacional, valorTotalItens);
    }
}
