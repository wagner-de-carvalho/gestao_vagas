package br.com.acme.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já cadastrado");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
