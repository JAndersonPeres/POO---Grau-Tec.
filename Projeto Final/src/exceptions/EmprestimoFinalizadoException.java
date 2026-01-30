package exceptions;

public class EmprestimoFinalizadoException extends BibliotecaException{

    public EmprestimoFinalizadoException(){
        super("Este empréstimo já foi finalizado");
    }

}
