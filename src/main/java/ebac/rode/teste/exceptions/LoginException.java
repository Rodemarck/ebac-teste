package ebac.rode.teste.exceptions;

public class LoginException extends Exception{
    public LoginException() {
        super("Login ou senha inválido.");
    }
}
