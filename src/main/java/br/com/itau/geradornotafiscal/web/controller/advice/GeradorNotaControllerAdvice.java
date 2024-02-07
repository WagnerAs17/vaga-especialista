package br.com.itau.geradornotafiscal.web.controller.advice;

import br.com.itau.geradornotafiscal.domain.exceptions.NotaFiscalException;
import br.com.itau.geradornotafiscal.domain.exceptions.RegiaoInformadaInvalidaException;
import br.com.itau.geradornotafiscal.domain.exceptions.RegimeTributacaoInvalidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeradorNotaControllerAdvice {


    @ExceptionHandler(value ={ NotaFiscalException.class })
    public ResponseEntity<Response> notaFiscalException(NotaFiscalException notaFiscalException){
        return ResponseEntity.unprocessableEntity()
                .body(Response.responseMessage(notaFiscalException.getMessage()));
    }

    @ExceptionHandler(value ={ RegiaoInformadaInvalidaException.class })
    public ResponseEntity<Response> regiaoInvalidaException(RegiaoInformadaInvalidaException notaFiscalException){
        return ResponseEntity.unprocessableEntity()
                .body(Response.responseMessage(notaFiscalException.getMessage()));
    }

    @ExceptionHandler(value ={ RegimeTributacaoInvalidoException.class })
    public ResponseEntity<Response> regimeTributacaoException(RegimeTributacaoInvalidoException notaFiscalException){
        return ResponseEntity.unprocessableEntity()
                .body(Response.responseMessage(notaFiscalException.getMessage()));
    }

    public static class Response{

        public String message;

        public Response(String message) {
            this.message = message;
        }

        public static Response responseMessage(String message){
            return new Response(message);
        }
    }
}
