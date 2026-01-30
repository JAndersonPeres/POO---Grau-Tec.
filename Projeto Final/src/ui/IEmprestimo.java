package ui;

import service.EmprestimoService;

import java.time.LocalDate;
import java.util.*;

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
                case 3 -> service.listarTodos().forEach(System.out::println);
                case 4 -> service.listarAtivos().forEach(System.out::println);
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
        service.realizarEmprestimo(isbn, cpf, dias);
        System.out.println("Empréstimos Realizado com Sucesso.");
    }

    private static void realizarDevolucao(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        service.realizarDevolucao(id);
        System.out.println("Devolução Realizada com Sucesso.");
    }

    private static void buscarPorID(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        System.out.println(service.buscarPorID(id));
    }

    private static void consultarPorCliente(Scanner sc, EmprestimoService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        service.buscarPorCliente(cpf).forEach(System.out::println);
    }

    private static void consultarMulta(Scanner sc, EmprestimoService service){
        System.out.print("ID do Empréstimo: ");
        String id = sc.nextLine();
        double multa = service.calcularMulta(service.buscarPorID(id), LocalDate.now());
        System.out.println("Multa Atual: R$" + String.format("%.2f", multa));
    }

}
