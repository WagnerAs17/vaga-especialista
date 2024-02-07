package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProdutoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculadoraAliquotaProdutoServiceImpl implements CalculadoraAliquotaProdutoService {
    @Override
    public List<ItemNotaFiscal> calcularAliquota(final List<Item> items, final double aliquotaPercentual) {
        final var itensNotaFiscal = new ArrayList<ItemNotaFiscal>();
        for (Item item : items) {
            double valorTributo = item.getValorUnitario() * aliquotaPercentual;
            ItemNotaFiscal itemNotaFiscal = ItemNotaFiscal.builder()
                    .idItem(item.getIdItem())
                    .descricao(item.getDescricao())
                    .valorUnitario(item.getValorUnitario())
                    .quantidade(item.getQuantidade())
                    .valorTributoItem(valorTributo)
                    .build();
            itensNotaFiscal.add(itemNotaFiscal);
        }
        return itensNotaFiscal;
    }
}



