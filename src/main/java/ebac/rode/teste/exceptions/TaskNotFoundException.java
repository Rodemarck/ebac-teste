package ebac.rode.teste.exceptions;

public class TaskNotFoundException  extends RuntimeException{
    public TaskNotFoundException() {
        super("Tarefa não encontrado.");
    }
}