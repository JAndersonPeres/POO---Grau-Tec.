package exceptions;

public class LimiteEmprestimosException extends BibliotecaException{

    public LimiteEmprestimosException(){
        super("Cliente atingiu o limite máximo de empréstimos");
    }

}
