package ui;

import model.Cliente;
import service.ClienteService;
import java.util.*;

import exceptions.ClienteNaoEncontradoException;

public class ICliente {

    public static void menu(Scanner sc, ClienteService service){
        int op;
        do{
            System.out.println("\n=== Menu Clientes ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Todos");
            System.out.println("3 - Listar Ativos");
            System.out.println("4 - Buscar por CPF");
            System.out.println("5 - Bloquer Cliente");
            System.out.println("6 - Atviar Cliente");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> cadastrar(sc, service);
                case 2 -> listarTodos(service);
                case 3 -> listarAtivos(service);
                case 4 -> buscarPorCpf(sc, service);
                case 5 -> bloquear(sc, service);
                case 6 -> ativar(sc, service);
                case 0 -> { continue; }
                default -> System.out.println("Erro: Opção Inválida.");
            }
        } while(op != 0);
    }

    private static void cadastrar(Scanner sc, ClienteService service){
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco, telefone, email);
        service.cadastrar(cliente);
        System.out.println("Cliente Cadastrado com Sucesso");
    }

    private static void listarTodos(ClienteService service){
        System.out.println("\n=== Todos os Clientes ===");
        for(Cliente c : service.listarTodos()){
            System.out.println(c);
            System.out.println("------------------------------");
        }
    }

    private static void listarAtivos(ClienteService service){
        System.out.println("\n=== Todos os Livros Disponívels ===");
        System.out.println("------------------------------");
        try{
            for(Cliente c : service.listarAtivos()){
                System.out.println(c);
                System.out.println("------------------------------");
            }
        } catch (ClienteNaoEncontradoException e){
            System.out.println("NENHUM CLIENTE ATIVO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorCpf(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            System.out.println(service.buscarPorCpf(cpf));
            System.out.println("------------------------------");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("NENHUM CLIENTE ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void bloquear(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        try {
            service.bloquearCliente(cpf);
            System.out.println("Cliente Desativado.");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Erro: Nenhum cliente encontrado com esse CPF.");
        }
    }

    private static void ativar(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        try {
            service.ativarCliente(cpf);
            System.out.println("Cliente Ativado.");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Erro: Nenhum cliente encontrado com esse CPF.");
        }
    }

}
