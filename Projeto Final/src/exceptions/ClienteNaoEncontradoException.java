package exceptions;

public class ClienteNaoEncontradoException extends BibliotecaException{

    public ClienteNaoEncontradoException(){
        super("Cliente não encontrado");
    }

}
