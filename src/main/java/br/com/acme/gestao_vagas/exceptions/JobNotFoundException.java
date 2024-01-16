package br.com.acme.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super();
    }

    public JobNotFoundException(String message) {
        super(message);
    }

}
