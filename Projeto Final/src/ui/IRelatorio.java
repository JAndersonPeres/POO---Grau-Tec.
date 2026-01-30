package ui;

import model.*;
import service.RelatorioService;
import java.util.*;

public class IRelatorio {

    public static void menu(Scanner sc, RelatorioService service){
        int op;
        do {
            System.out.println("=== Menu Relatórios ===");
            System.out.println("1 - Livros mais Emprestados");
            System.out.println("2 - Clientes mais Ativos");
            System.out.println("3 - Empréstimos em Atraso");
            System.out.println("4 - Estatísticas Gerais");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> livrosMaisEmprestados(service);
                case 2 -> clientesAtivos(service);
                case 3 -> emprestimosAtrasados(service);
                case 4 -> estatisticasGerais(service);
            }
        } while (op != 0);
    }

    private static void livrosMaisEmprestados(RelatorioService service){
        System.out.println("\n--- Livros Mais Emprestrados ---");
        List<Livro> ranking = service.livrosMaisEmprestados(5);
        ranking.forEach(Livro -> System.out.println(Livro.getTitulo() + "| Empréstimos: " + Livro.getTotalEmprestimos()));
    }

    private static void clientesAtivos(RelatorioService service){
        System.out.println("\n--- Clientes Ativos ---");
        List<Cliente> ranking = service.clientesMaisAtivos(5);
        ranking.forEach(Cliente -> System.out.println(Cliente.getNome()));
    }

    private static void emprestimosAtrasados(RelatorioService service){
        System.out.println("\n--- Livro em Atraso ---");
        for(Emprestimo e : service.emprestimosEmAtraso()){
            System.out.println("Livro: " + e.getLivro().getTitulo() + " | Cliente: " + e.getCliente().getNome() + " | Vencimento: " + e.getDataPrevistaDevolucao());
        }
    }

    private static void estatisticasGerais(RelatorioService service){
        System.out.println("\n--- Estatísticas Gerais ---");
        Map<String, Object> status = service.estatisticasGerais();
        System.out.println("Total de Empréstimos: " + status.get("totalEmprestimos").toString());
        System.out.println("Empréstimos Ativos: " + status.get("emprestimosAtivos").toString());
        System.out.println("Empréstimos Atrasados" + status.get("emprestimosAtrasado").toString());
        System.out.println("Total em Multas: R$" + String.format("%.2f", status.get("totalMultas")));
    }

}
