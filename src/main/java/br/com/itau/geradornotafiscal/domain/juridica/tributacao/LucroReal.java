package br.com.itau.geradornotafiscal.domain.juridica.tributacao;

import br.com.itau.geradornotafiscal.domain.juridica.aliquota.lucroreal.AliquotaLucroReal;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.utils.AliquotaUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LucroReal implements RegimeTributacao{

    private final List<AliquotaLucroReal> aliqutoasLucroReal;

    public LucroReal(final List<AliquotaLucroReal> aliqutoasLucroReal) {
        Objects.requireNonNull(aliqutoasLucroReal);
        this.aliqutoasLucroReal = AliquotaUtils.sorted(aliqutoasLucroReal);
    }

    @Override
    public RegimeTributacaoPJ getRegimeTributacao() {
        return RegimeTributacaoPJ.LUCRO_REAL;
    }

    @Override
    public double getAliquota(double valorTotalItens) {
        return AliquotaUtils.getAliquotaFromAliquotas(aliqutoasLucroReal, valorTotalItens);
    }
}
