package br.com.itau.geradornotafiscal.service;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;

import java.util.List;

public interface CalculadoraAliquotaProdutoService {

    List<ItemNotaFiscal> calcularAliquota(List<Item> items, double aliquotaPercentual);
}
