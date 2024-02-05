package br.com.itau.geradornotafiscal.port.out;

import br.com.itau.geradornotafiscal.model.NotaFiscal;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class EntregaIntegrationPort {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void criarAgendamentoEntrega(NotaFiscal notaFiscal) {
        try {
            //Simula o agendamento da entrega
            threadPoolTaskExecutor.submit(() -> {
                try {
                    if (notaFiscal.getItens().size() > 5) {
                    /* Aqui está o problema de performance do aplicacao para pedidos com mais de 5 itens
                        Se voce chegou ate aqui basta remover esse valor de 5s para 'solucionar' o misterio
                    * */
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
