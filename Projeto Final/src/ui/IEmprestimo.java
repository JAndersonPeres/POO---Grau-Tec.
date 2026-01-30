package ui;

import service.EmprestimoService;

import java.time.LocalDate;
import java.util.*;

import exceptions.ClienteInativoException;
import exceptions.EmprestimoFinalizadoException;
import exceptions.EmprestimoNaoAtivoException;
import exceptions.EmprestimoNaoEncontradoException;
import exceptions.LimiteEmprestimosException;
import exceptions.LivroIndisponivelException;
import exceptions.LivroNaoEncontradoException;
import model.Emprestimo;
import model.Livro;

public class IEmprestimo {

    public static void menu(Scanner sc, EmprestimoService service){
        int op;
        do {
            System.out.println("\n=== Menu Empréstimos ===");
            System.out.println("1 - Realizar Empréstimo");
            System.out.println("2 - Realizar Devolução");
            System.out.println("3 - Listar Todos os Empréstimos");
            System.out.println("4 - Listar Empréstimos Ativos");
            System.out.println("5 - Buscar Empréstimo por ID");
            System.out.println("6 - Consultar Empréstimos do Cliente");
            System.out.println("7 - Consultar Multa de um Empréstimo");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> realizarEmprestimo(sc, service);
                case 2 -> realizarDevolucao(sc, service);
                case 3 -> listarTodos(service);
                case 4 -> listarAtivos(service);
                case 5 -> buscarPorID(sc, service);
                case 6 -> consultarPorCliente(sc, service);
                case 7 -> consultarMulta(sc, service);
            }
        } while (op != 0);
    }

    private static void realizarEmprestimo(Scanner sc, EmprestimoService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        System.out.print("ISBN do Livro: ");
        String isbn = sc.nextLine();
        System.out.print("Quantidade de dias: ");
        int dias = Integer.parseInt(sc.nextLine());
        try{
            service.realizarEmprestimo(isbn, cpf, dias);
            System.out.println("Empréstimos Realizado com Sucesso.");
        } catch (ClienteInativoException e) {
            System.out.println("Erro: Cliente Socilitado está Inativo.");
        } catch (LimiteEmprestimosException e) {
            System.out.println("Erro: Limite de Empréstimo Excessedido.");
        } catch (LivroIndisponivelException e) {
            System.out.println("Erro: Livro Indisponível para Empréstimo.");
        }
    }

    private static void realizarDevolucao(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        try {
            service.realizarDevolucao(id);
            System.out.println("Devolução Realizada com Sucesso.");
        } catch (EmprestimoFinalizadoException e) {
            System.out.println("Erro: Empréstimo Requerido já foi Finalizado.");
        }
    }

    private static void listarTodos(EmprestimoService service){
        System.out.println("\n=== Todos os Empréstimos ===");
        for(Emprestimo e : service.listarTodos()){
            System.out.println(e);
            System.out.println("------------------------------");
        }
    }

    private static void listarAtivos(EmprestimoService service){
        System.out.println("\n=== Todos os Empréstimos Ativos ===");
        System.out.println("------------------------------");
        try{
            for(Emprestimo e : service.listarAtivos()){
                System.out.println(e);
                System.out.println("------------------------------");
            }
        } catch (EmprestimoNaoEncontradoException e){
            System.out.println("NENHUM EMPRÉSTIMO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorID(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try {
            System.out.println(service.buscarPorID(id));
            System.out.println("------------------------------");
        } catch (EmprestimoNaoEncontradoException e) {
            System.out.println("NENHUM EMPRÉSTIMO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void consultarPorCliente(Scanner sc, EmprestimoService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try {
            for(Emprestimo em : service.buscarPorCliente(cpf)){
                System.out.println(em);
                System.out.println("------------------------------");
            }
        } catch (EmprestimoNaoAtivoException e) {
            System.out.println("NENHUM EMPRÉSTIMO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void consultarMulta(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        try {
            double multa = service.calcularMulta(service.buscarPorID(id), LocalDate.now());
            System.out.println("Multa Atual: R$" + String.format("%.2f", multa));
        } catch (EmprestimoNaoEncontradoException e){
            System.out.println("Erro: Nenhum Empréstimo com esse ID foi Encontrado.");
        }
    }

}
