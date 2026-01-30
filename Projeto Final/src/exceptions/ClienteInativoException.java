package exceptions;

public class ClienteInativoException extends BibliotecaException{

    public ClienteInativoException(){
        super("Cliente está inativo e não pode realizar empréstimos");
    }

}
