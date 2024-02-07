package br.com.itau.geradornotafiscal.domain.frete.utils;

import br.com.itau.geradornotafiscal.domain.exceptions.RegiaoInformadaInvalidaException;
import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Regiao;

import java.util.Objects;
import java.util.function.Predicate;

public class FreteUtils {

    private FreteUtils(){}

    public static Regiao getRegionPorEntregaEFidelidade(Destinatario destinatario){
        return destinatario.getEnderecos().stream()
                .filter(end -> filterEndereco(end) && Objects.nonNull(end.getRegiao()))
                .map(Endereco::getRegiao)
                .findFirst()
                .orElseThrow(() -> new RegiaoInformadaInvalidaException("Regiao invalida"));
    }

    private static boolean filterEndereco(Endereco endereco) {
        return finalidadeIsEntrega(endereco) ||
                finalidadeIsCobranca(endereco);
    }

    private static boolean finalidadeIsEntrega(Endereco endereco){
        return endereco.getFinalidade() == Finalidade.ENTREGA;
    }
    private static boolean finalidadeIsCobranca(Endereco endereco){
        return endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA;
    }
}
