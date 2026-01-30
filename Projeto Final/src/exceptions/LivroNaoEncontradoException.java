package exceptions;

public class LivroNaoEncontradoException extends BibliotecaException {

    public LivroNaoEncontradoException(){
        super("Livro não encontrado");
    }

}
