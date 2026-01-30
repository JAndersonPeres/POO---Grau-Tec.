package exceptions;

public class LivroIndisponivelException extends BibliotecaException{

    public LivroIndisponivelException(){
        super("Livro indisponível para empréstimo");
    }

}
