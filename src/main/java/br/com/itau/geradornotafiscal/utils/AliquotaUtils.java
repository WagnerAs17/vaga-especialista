package br.com.itau.geradornotafiscal.utils;

import br.com.itau.geradornotafiscal.domain.Aliquota;
import br.com.itau.geradornotafiscal.domain.exceptions.AliquotaNaoEncontradaException;
import br.com.itau.geradornotafiscal.domain.fisica.aliquota.AliquotaPF;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AliquotaUtils {

    private AliquotaUtils(){}

    public static <T extends Aliquota> List<T> sorted(final List<T> aliquotas){
        return aliquotas.stream()
                .sorted(Comparator.comparing(Aliquota::getAliquota))
                .collect(Collectors.toList());
    }

    public static <T extends Aliquota> double getAliquotaFromAliquotas(final List<T> aliquotas, double valorTotalItems){
        return aliquotas.
                stream()
                .filter(aliquot -> aliquot.isElegivel(valorTotalItems))
                .map(Aliquota::getAliquota)
                .findFirst().orElseThrow(() -> new AliquotaNaoEncontradaException("Aliquota nao encontrada para o valor total de items"));
    }
}
