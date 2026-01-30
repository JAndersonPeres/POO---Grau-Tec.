package exceptions;

public class EmprestimoNaoAtivoException extends BibliotecaException{

    public EmprestimoNaoAtivoException(){
        super("Nenhum Empréstimo Ativo.");
    }

}
