package br.com.itau.calculadoratributos.controller;

import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.TipoPessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest
public class GeradorNFControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void givenAValidPedido_whenCallsGerarNotaFiscalEndpoint_shouldReturnAValidNotaFiscal() throws Exception {
        //given
        final var expectedValorTotalItens = 800.0;
        final var aliquota = 0.03;
        final var valorFrete = 100;
        final var expectedValorFrete = 106.0;
        final var pedido = new Pedido();
        pedido.setItens(itens());
        pedido.setValorFrete(valorFrete);
        pedido.setDestinatario(destinario());
        pedido.setValorTotalItens(expectedValorTotalItens);
        pedido.setIdPedido(1);
        pedido.setData(LocalDate.now());
        final var expectedValorTributoTvSamsung = pedido.getItens().get(0).getValorUnitario() * aliquota;
        final var expectedValorLaptopIntel = pedido.getItens().get(1).getValorUnitario() * aliquota;
        final var expectedItensCount = 2;


        final var request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.valor_total_itens", equalTo(expectedValorTotalItens)))
                .andExpect(jsonPath("$.valor_frete", equalTo(expectedValorFrete)))
                .andExpect(jsonPath("$.itens[0].valor_tributo_item", equalTo(expectedValorTributoTvSamsung)))
                .andExpect(jsonPath("$.itens[1].valor_tributo_item", equalTo(expectedValorLaptopIntel)))
                .andExpect(jsonPath("$.itens", hasSize(expectedItensCount)));
    }

    @Test
    public void givenAValidPedido_whenCallsGerarNotaFiscalEndpointTwice_shouldReturnJustTwoItensNotaFiscal() throws Exception {
        //given
        final var expectedValorTotalItens = 800.0;
        final var aliquota = 0.03;
        final var valorFrete = 100;
        final var expectedValorFrete = 106.0;
        final var pedido = new Pedido();
        pedido.setItens(itens());
        pedido.setValorFrete(valorFrete);
        pedido.setDestinatario(destinario());
        pedido.setValorTotalItens(expectedValorTotalItens);
        pedido.setIdPedido(1);
        pedido.setData(LocalDate.now());
        final var expectedValorTributoTvSamsung = pedido.getItens().get(0).getValorUnitario() * aliquota;
        final var expectedValorLaptopIntel = pedido.getItens().get(1).getValorUnitario() * aliquota;
        final var expectedItensCount = 2;


        var request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.valor_total_itens", equalTo(expectedValorTotalItens)))
                .andExpect(jsonPath("$.valor_frete", equalTo(expectedValorFrete)))
                .andExpect(jsonPath("$.itens[0].valor_tributo_item", equalTo(expectedValorTributoTvSamsung)))
                .andExpect(jsonPath("$.itens[1].valor_tributo_item", equalTo(expectedValorLaptopIntel)))
                .andExpect(jsonPath("$.itens", hasSize(expectedItensCount)));

        request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.valor_total_itens", equalTo(expectedValorTotalItens)))
                .andExpect(jsonPath("$.valor_frete", equalTo(expectedValorFrete)))
                .andExpect(jsonPath("$.itens[0].valor_tributo_item", equalTo(expectedValorTributoTvSamsung)))
                .andExpect(jsonPath("$.itens[1].valor_tributo_item", equalTo(expectedValorLaptopIntel)))
                .andExpect(jsonPath("$.itens", hasSize(expectedItensCount)));
    }

    @Test
    public void givenAnInvalidPedidoWithValorTotalItensIncorrect_whenCallsGerarNotaFiscal_ShouldReceive422() throws Exception {
        //given
        final var expectedValorTotalItens = 100.0;
        final var valorFrete = 100;
        final var pedido = new Pedido();
        final var expectedErrorMessage = "valor total dos itens nao bate com o informado";
        pedido.setItens(itens());
        pedido.setValorFrete(valorFrete);
        pedido.setDestinatario(destinario());
        pedido.setValorTotalItens(expectedValorTotalItens);
        pedido.setIdPedido(1);
        pedido.setData(LocalDate.now());


        final var request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message", equalTo(expectedErrorMessage)));
    }

    @Test
    public void givenAnInvalidPedidoWithUnkownRegiao_whenCallsGerarNotaFiscal_ShouldReceive422() throws Exception {
        //given
        final var expectedValorTotalItens = 100.0;
        final var valorFrete = 100;
        final var pedido = new Pedido();
        final var expectedErrorMessage = "Regiao invalida";
        pedido.setItens(itens());
        pedido.setValorFrete(valorFrete);
        pedido.setDestinatario(destinarioComEnderecoSemRegiao());
        pedido.setValorTotalItens(expectedValorTotalItens);
        pedido.setIdPedido(1);
        pedido.setData(LocalDate.now());


        final var request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message", equalTo(expectedErrorMessage)));
    }

    @Test
    public void givenAnInvalidPedidoWithInvalidTributacao_whenCallsGerarNotaFiscal_ShouldReceive422() throws Exception {
        //given
        final var expectedValorTotalItens = 100.0;
        final var valorFrete = 100;
        final var pedido = new Pedido();
        final var expectedErrorMessage = "Regime de tribucao informado nao eh valido";
        pedido.setItens(itens());
        pedido.setValorFrete(valorFrete);
        pedido.setDestinatario(destinarioComTributacaoInvalida());
        pedido.setValorTotalItens(expectedValorTotalItens);
        pedido.setIdPedido(1);
        pedido.setData(LocalDate.now());


        final var request = post("/api/pedido/gerarNotaFiscal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pedido));

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message", equalTo(expectedErrorMessage)));
    }

    private List<Item> itens(){
        final var tvLcdSamsung = new Item();
        tvLcdSamsung.setIdItem(UUID.randomUUID().toString());
        tvLcdSamsung.setDescricao("TV LCD Samsung");
        tvLcdSamsung.setValorUnitario(50);
        tvLcdSamsung.setQuantidade(8);

        final var laptopIntel = new Item();
        laptopIntel.setIdItem(UUID.randomUUID().toString());
        laptopIntel.setDescricao("Laptop Intel Core");
        laptopIntel.setValorUnitario(40);
        laptopIntel.setQuantidade(10);

        return List.of(tvLcdSamsung, laptopIntel);
    }

    private Destinatario destinario(){
        return  Destinatario.builder()
                .enderecos(enderecos())
                .regimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO)
                .tipoPessoa(TipoPessoa.JURIDICA)
                .build();
    }

    private List<Endereco> enderecos(){
        var endereco = Endereco.builder()
                .cep(UUID.randomUUID().toString())
                .finalidade(Finalidade.ENTREGA)
                .regiao(Regiao.SUL)
                .build();

        return List.of(endereco);
    }

    private Destinatario destinarioComEnderecoSemRegiao(){
        return  Destinatario.builder()
                .enderecos(enderecosSemRegiao())
                .regimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO)
                .tipoPessoa(TipoPessoa.JURIDICA)
                .build();
    }

    private Destinatario destinarioComTributacaoInvalida(){
        return  Destinatario.builder()
                .enderecos(enderecos())
                .regimeTributacao(null)
                .tipoPessoa(TipoPessoa.JURIDICA)
                .build();
    }

    private List<Endereco> enderecosSemRegiao(){
        var endereco = Endereco.builder()
                .cep(UUID.randomUUID().toString())
                .finalidade(Finalidade.ENTREGA)
                .regiao(null)
                .build();

        return List.of(endereco);
    }


}
