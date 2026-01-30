package ui;

import java.util.*;

import exceptions.LivroNaoEncontradoException;
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
                case 2 -> listarTodos(service);
                case 3 -> listarDisponiveis(service);
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
        try{
            Livro livro = new Livro(titulo, autor, isbn, editora, ano);
            service.cadastrar(livro);
            System.out.println("Livro Cadastrado com Sucesso.");
        } catch (IllegalArgumentException e){
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void listarTodos(LivroService service){
        System.out.println("\n=== Todos os Livros ===");
        for(Livro l : service.listarTodos()){
            System.out.println(l);
            System.out.println("------------------------------");
        }
    }

    private static void listarDisponiveis(LivroService service){
        System.out.println("\n=== Todos os Livros Disponívels ===");
        System.out.println("------------------------------");
        try{
            for(Livro l : service.listarDisponiveis()){
                System.out.println(l);
                System.out.println("------------------------------");
            }
        } catch (LivroNaoEncontradoException e){
            System.out.println("NENHUM LIVRO DISPONÍVEL");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorTitulo(Scanner sc, LivroService service){
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            for(Livro l : service.buscarPorTitulo(titulo)){
                System.out.println(l);
                System.out.println("------------------------------");
            }
        } catch (LivroNaoEncontradoException e) {
            System.out.println("NENHUM TÍTULO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorAutor(Scanner sc, LivroService service){
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            for(Livro l : service.buscarPorAutor(autor)){
                System.out.println(l);
                System.out.println("------------------------------");
            }
        } catch (LivroNaoEncontradoException e) {
            System.out.println("NENHUM AUTOR ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorIsbn(Scanner sc, LivroService service){
        System.out.print("ISBN: ");
        String ISBN = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            System.out.println(service.buscarPorIsbn(ISBN));
            System.out.println("------------------------------");
        } catch (LivroNaoEncontradoException e) {
            System.out.println("NENHUM LIVRO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorEditora(Scanner sc, LivroService service){
        System.out.print("Editora: ");
        String editora = sc.nextLine();
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            for(Livro l : service.buscarPorEditora(editora)){
                System.out.println(l);
                System.out.println("------------------------------");
            }
        } catch (LivroNaoEncontradoException e) {
            System.out.println("NENHUMA EDITORA ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

    private static void buscarPorAno(Scanner sc, LivroService service){
        System.out.print("Ano de Publicação: ");
        int ano = Integer.parseInt(sc.nextLine());
        System.out.println("\n=== Resultado da Busca ===");
        System.out.println("------------------------------");
        try{
            for(Livro l : service.buscarPorAno(ano)){
                System.out.println(l);
                System.out.println("------------------------------");
            }
        } catch (LivroNaoEncontradoException e) {
            System.out.println("NENHUMA LIVRO ENCONTRADO");
            System.out.println("------------------------------");
        }
    }

}
