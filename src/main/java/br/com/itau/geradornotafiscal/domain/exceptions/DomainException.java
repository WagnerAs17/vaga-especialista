package br.com.itau.geradornotafiscal.domain.exceptions;

public class DomainException extends NoStackTraceException{
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
