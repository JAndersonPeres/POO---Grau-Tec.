import model.*;
import service.*;
import util.*;
import ui.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        List<Livro> livros = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Emprestimo> emprestimos = new ArrayList<>();
        DataLoader.carregarTudo(livros, clientes, emprestimos);
        LivroService livroService = new LivroService(livros);
        ClienteService clienteService = new ClienteService(clientes);
        EmprestimoService emprestimoService = new EmprestimoService(livroService, clienteService, emprestimos);
        RelatorioService relatorioService = new RelatorioService(livroService, clienteService, emprestimoService);

        Scanner sc = new Scanner(System.in);
        int op;
        do{
            System.out.println("\n=== Sistema de Gestão de Biblioteca ===");
            System.out.println("1 - Livros");
            System.out.println("2 - Clientes");
            System.out.println("3 - Empréstimos");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            op = Integer.parseInt(sc.nextLine());
            try {
                switch (op){
                    case 1 -> ILivro.menu(sc, livroService);
                    case 2 -> ICliente.menu(sc, clienteService);
                    case 3 -> IEmprestimo.menu(sc, emprestimoService);
                    case 4 -> IRelatorio.menu(sc, relatorioService);
                    case 0 -> {
                        DataSaver.salvarTudo(livros, clientes, emprestimos);
                        System.out.println("Dados Salvos. Encerrando Sistema.");
                    }
                    default -> System.out.println("Opção Inválida.");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("Erro: " + e.getMessage());
            }
        } while (op != 0);

        sc.close();
    }
}
