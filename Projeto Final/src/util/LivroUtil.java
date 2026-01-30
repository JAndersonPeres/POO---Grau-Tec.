package util;

import model.Livro;

public class LivroUtil {

    public static String toLinha(Livro livro){
        return String.join(";", livro.getTitulo(), livro.getAutor(), livro.getIsbn(), livro.getEditora(), String.valueOf(livro.getAnoPublicacao()), String.valueOf(livro.isDisponivel()), String.valueOf(livro.getTotalEmprestimos()));
    }

    public static Livro fromLinha(String linha){
        String [] p = linha.split(";");
        Livro livro = new Livro(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]));

        if(!Boolean.parseBoolean(p[5])) livro.emprestar();
        for(int i = 0; i< Integer.parseInt(p[6]); i++){
            livro.devolver();
            livro.emprestar();
        }

        return livro;
    }

}
