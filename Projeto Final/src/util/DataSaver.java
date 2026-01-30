package util;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataSaver {

    private static final String ARQ_LIVRO = "dados/livros.txt";
    private static final String ARQ_CLIENTE = "dados/clientes.txt";
    private static final String ARQ_EMPRESTIMO = "dados/emprestimos.txt";

    public static void salvarLivros(List<Livro> livros){
        ArquivoUtil.escreverLinhas(ARQ_LIVRO, livros.stream().map(LivroUtil::toLinha).collect(Collectors.toList()));
    }

    public static void salvarClientes(List<Cliente> clientes){
        ArquivoUtil.escreverLinhas(ARQ_CLIENTE, clientes.stream().map(ClienteUtil::toLinha).collect(Collectors.toList()));
    }

    public static void salvarEmprestimos(List<Emprestimo> emprestimos){
        ArquivoUtil.escreverLinhas(ARQ_EMPRESTIMO, emprestimos.stream().map(EmprestimoUtil::toLinha).collect(Collectors.toList()));
    }

    public static void salvarTudo(List<Livro> l, List<Cliente> c, List<Emprestimo> e){
        salvarLivros(l);
        salvarClientes(c);
        salvarEmprestimos(e);
    }

}
