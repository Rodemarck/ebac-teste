package ebac.rode.teste.exceptions;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("Login ou senha inválido.");
    }
}
