package util;

import model.*;
import java.util.*;

public class DataLoader {

    private static final String ARQ_LIVRO = "dados/livros.txt";
    private static final String ARQ_CLIENTE = "dados/clientes.txt";
    private static final String ARQ_EMPRESTIMO = "dados/emprestimos.txt";

    public static List<Livro> carregarLivros(){
        List<Livro> livros = new ArrayList<>();
        for(String linha : ArquivoUtil.lerLinhas(ARQ_LIVRO)){
            livros.add(LivroUtil.fromLinha(linha));
        }
        return livros;
    }

    public static List<Cliente> carregarClientes(){
        List<Cliente> clientes = new ArrayList<>();
        for(String linha : ArquivoUtil.lerLinhas(ARQ_CLIENTE)){
            clientes.add(ClienteUtil.fromLinha(linha));
        }
        return clientes;
    }

    public static List<Emprestimo> carregarEmprestimos(List<Livro> livros, List<Cliente> clientes){
        Map<String, Livro> livrosPorIsbn = new HashMap<>();
        for(Livro l : livros){
            livrosPorIsbn.put(l.getIsbn(), l);
        }
        Map<String, Cliente> clientesPorCpf = new HashMap<>();
        for(Cliente c : clientes){
            clientesPorCpf.put(c.getCpf(), c);
        }
        List<Emprestimo> emprestimos = new ArrayList<>();
        for(String linha : ArquivoUtil.lerLinhas(ARQ_EMPRESTIMO)){
            String [] p = linha.split(";");
            String isbn = p[1];
            String cpf = p[2];
            Livro livro = livrosPorIsbn.get(isbn);
            Cliente cliente = clientesPorCpf.get(cpf);

            if(livro != null && cliente != null){
                Emprestimo e = EmprestimoUtil.fromLinha(linha, livro, cliente);
                emprestimos.add(e);
            }
        }
        return emprestimos;
    }

    public static void carregarTudo(List<Livro> livros, List<Cliente> clientes, List<Emprestimo> emprestimos){
        livros = carregarLivros();
        clientes = carregarClientes();
        emprestimos = carregarEmprestimos(livros, clientes);
    }

}
