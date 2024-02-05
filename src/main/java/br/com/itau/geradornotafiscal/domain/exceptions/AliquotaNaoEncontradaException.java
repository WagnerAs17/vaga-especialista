package br.com.itau.geradornotafiscal.domain.exceptions;

public class AliquotaNaoEncontradaException extends DomainException{
    public AliquotaNaoEncontradaException(String message) {
        super(message);
    }

    public AliquotaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
