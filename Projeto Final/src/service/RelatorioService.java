package service;

import model.*;
import java.util.*;
import java.time.LocalDate;

public class RelatorioService {

    private final LivroService livroService;
    private final ClienteService clienteService;
    private final EmprestimoService emprestimoService;

    public RelatorioService(LivroService livroService, ClienteService clienteService, EmprestimoService emprestimoService){
        this.livroService = livroService;
        this.clienteService = clienteService;
        this.emprestimoService = emprestimoService;
    }

    public List<Livro> livrosMaisEmprestados(int limite){
        return livroService.listarTodos().stream().sorted(Comparator.comparingInt(Livro::getTotalEmprestimos).reversed()).limit(limite).toList();
    }

    public List<Cliente> clientesMaisAtivos(int limite){
        return clienteService.listarTodos().stream().sorted(Comparator.comparingInt(Cliente::getEmprestimosAtivos).reversed()).limit(limite).toList();
    }

    public List<Emprestimo> emprestimosEmAtraso(){
        LocalDate hoje = LocalDate.now();
        return emprestimoService.listarAtivos().stream().filter(e -> e.estaAtrasado(hoje)).toList();
    }

    public Map<String, Object> estatisticasGerais(){
        List<Emprestimo> todos = emprestimoService.listarTodos();

        long totalEmprestimos = todos.size();
        long emprestimosAtivos = todos.stream().filter(Emprestimo::estaAtivo).count();
        long emprestimosAtrasados = todos.stream().filter(e -> e.estaAtrasado(LocalDate.now())).count();
        double totalMultas = todos.stream().mapToDouble(Emprestimo::getMulta).sum();

        return Map.of("totalEmprestimos", totalEmprestimos, "emprestimosAtivos", emprestimosAtivos, "emprestimosAtrasado", emprestimosAtrasados, "totalMultas", totalMultas);
    }

}
