package service;

import model.*;
import exceptions.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmprestimoService {

    private static final double VALOR_DIA = 1.5;

    private final LivroService livroService;
    private final ClienteService clienteService;
    private final List<Emprestimo> emprestimos;

    public EmprestimoService(LivroService livroService, ClienteService clienteService, List<Emprestimo> emprestimos){
        this.livroService = livroService;
        this.clienteService = clienteService;
        this.emprestimos = emprestimos;
    }

    public Emprestimo realizarEmprestimo(String isbn, String cpf, int dias){
        Livro livro = livroService.buscarPorIsbn(isbn);
        Cliente cliente = clienteService.buscarPorCpf(cpf);

        if(!cliente.isAtivo()){ throw new ClienteInativoException(); }
        if(cliente.getEmprestimosAtivos() >= Cliente.LIMITE_EMPRESTIMOS){ throw new LimiteEmprestimosException(); }
        if(!livro.isDisponivel()){ throw new LivroIndisponivelException(); }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(dias);
        Emprestimo emprestimo = new Emprestimo(gerarId(), livro, cliente, hoje, devolucaoPrevista);
        cliente.registrarEmprestimo();
        livro.emprestar();
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public void realizarDevolucao(String idEmprestimo){
        Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);

        if(!emprestimo.estaAtivo()){ throw new EmprestimoFinalizadoException(); }

        LocalDate hoje = LocalDate.now();
        double multa = calcularMulta(emprestimo, hoje);
        emprestimo.registrarDevolucao(hoje, multa);
        emprestimo.getLivro().devolver();
        emprestimo.getCliente().registrarDevolucao();
    }

    public double calcularMulta(Emprestimo emprestimo, LocalDate hoje){
        if(!emprestimo.estaAtrasado(hoje)){ return 0.0; }
        long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevistaDevolucao(), hoje);
        return diasAtraso * VALOR_DIA;
    }

    private Emprestimo buscarEmprestimoPorId(String id){
        for(Emprestimo e : emprestimos){
            if(e.getId().equals(id)) return e;
        }
        throw new EmprestimoNaoEncontradoException();
    }

    private String gerarId(){
        return "ID-" + System.currentTimeMillis();
    }

    public List<Emprestimo> listarTodos(){ return List.copyOf(emprestimos); }

    public List<Emprestimo> listarAtivos(){
        List<Emprestimo> filtragem = new ArrayList<>();
        for(Emprestimo e : emprestimos){
            if(e.estaAtivo()) filtragem.add(e);
        }
        if(filtragem.isEmpty()) throw new EmprestimoNaoEncontradoException();
        return filtragem;
    }

    public List<Emprestimo> listarAtrasados(){
        List<Emprestimo> filtragem = new ArrayList<>();
        LocalDate hoje = LocalDate.now();
        for(Emprestimo e : emprestimos){
            if(e.estaAtrasado(hoje)) filtragem.add(e);
        }
        if(filtragem.isEmpty()) throw new EmprestimoNaoEncontradoException();
        return filtragem;
    }

    public List<Emprestimo> buscarPorCliente(String cpf){
        List<Emprestimo> filtragem = new ArrayList<>();
        for(Emprestimo e : emprestimos){
            if(e.getCliente().getCpf().equals(cpf)) filtragem.add(e);
        }
        if(filtragem.isEmpty()) throw new EmprestimoNaoAtivoException();
        return filtragem;
    }

    public Emprestimo buscarPorID(String id){
        for(Emprestimo e : emprestimos){
            if(e.getId().equals(id)) return e;
        }
        throw new EmprestimoNaoEncontradoException();
    }

}
