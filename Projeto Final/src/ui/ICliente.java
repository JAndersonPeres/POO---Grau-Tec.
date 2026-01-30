package ui;

import model.Cliente;
import service.ClienteService;
import java.util.*;

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
                case 2 -> service.listarTodos().forEach(System.out::println);
                case 3 -> service.listarAtivos().forEach(System.out::println);
                case 4 -> buscarPorCpf(sc, service);
                case 5 -> bloquear(sc, service);
                case 6 -> ativar(sc, service);
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

    private static void buscarPorCpf(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        System.out.println(service.buscarPorCpf(cpf));
    }

    private static void bloquear(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        service.bloquearCliente(cpf);
        System.out.println("Cliente Desativado.");
    }

    private static void ativar(Scanner sc, ClienteService service){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        service.bloquearCliente(cpf);
        System.out.println("Cliente Ativado.");
    }

}
