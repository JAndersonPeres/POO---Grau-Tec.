package ui;

import java.util.*;
import service.LivroService;
import model.Livro;

public class ILivro{

    public static void menu(Scanner sc, LivroService service){
        int op;
        do {
            System.out.println("\n=== Menu Livros ===");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Todos");
            System.out.println("3 - Listar Disponíveis");
            System.out.println("4 - Buscar por Título");
            System.out.println("5 - Buscar por Autor");
            System.out.println("6 - Buscar por ISBN");
            System.out.println("7 - Buscar por Editora");
            System.out.println("8 - Buscar por Ano de Publicação");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> cadastrar(sc, service);
                case 2 -> service.listarTodos().forEach(System.out::println);
                case 3 -> service.listarDisponiveis().forEach(System.out::println);
                case 4 -> buscarPorTitulo(sc, service);
                case 5 -> buscarPorAutor(sc, service);
                case 6 -> buscarPorIsbn(sc, service);
                case 7 -> buscarPorEditora(sc, service);
                case 8 -> buscarPorAno(sc, service);
            }
        } while (op != 0);
    }

    private static void cadastrar(Scanner sc, LivroService service){
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Editora: ");
        String editora = sc.nextLine();
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(sc.nextLine());
        Livro livro = new Livro(titulo, autor, isbn, editora, ano);
        service.cadastrar(livro);
        System.out.println("Livro Cadastrado com Sucesso.");
    }

    private static void buscarPorTitulo(Scanner sc, LivroService service){
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        service.buscarPorTitulo(titulo).forEach(System.out::println);
    }

    private static void buscarPorAutor(Scanner sc, LivroService service){
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        service.buscarPorTitulo(autor).forEach(System.out::println);
    }

    private static void buscarPorIsbn(Scanner sc, LivroService service){
        System.out.print("ISBN: ");
        String ISBN = sc.nextLine();
        System.out.println(service.buscarPorTitulo(ISBN));
    }

    private static void buscarPorEditora(Scanner sc, LivroService service){
        System.out.print("Editora: ");
        String editora = sc.nextLine();
        service.buscarPorTitulo(editora).forEach(System.out::println);
    }

    private static void buscarPorAno(Scanner sc, LivroService service){
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(sc.nextLine());
        service.buscarPorAno(ano).forEach(System.out::println);
    }

}
