package br.com.itau.geradornotafiscal.web.controller;

import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.GeradorNotaFiscalService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
@Slf4j
public class GeradorNFController {

	private final GeradorNotaFiscalService notaFiscalService;

	@PostMapping("/gerarNotaFiscal")
	@Timed
	public ResponseEntity<NotaFiscal> gerarNotaFiscal(@RequestBody Pedido pedido) {
		// Lógica de processamento do pedido

		// Aqui você pode realizar as operações desejadas com o objeto Pedido

		// Exemplo de retorno
		String mensagem = "Nota fiscal gerada com sucesso para o pedido: " + pedido.getIdPedido();
		NotaFiscal notaFiscal = notaFiscalService.gerarNotaFiscal(pedido);
		return new ResponseEntity<>(notaFiscal, HttpStatus.OK);
	}

	
}
