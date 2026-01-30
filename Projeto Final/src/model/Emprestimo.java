package model;

import java.time.LocalDate;

public class Emprestimo {

    private String id;
    private Livro livro;
    private Cliente cliente;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private double multa;
    private StatusEmprestimo status;

    public Emprestimo(String id,  Livro livro, Cliente cliente, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {

        if (dataPrevistaDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data prevista inválida");
        }

        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.status = StatusEmprestimo.ATIVO;
        this.multa = 0.0;
    }

    public boolean estaAtivo() {  return status == StatusEmprestimo.ATIVO;  }
    public boolean estaAtrasado(LocalDate dataAtual) { return status == StatusEmprestimo.ATIVO && dataAtual.isAfter(dataPrevistaDevolucao); }

    public void registrarDevolucao(LocalDate dataDevolucao, double multa) {
        this.dataDevolucao = dataDevolucao;
        this.multa = multa;
        this.status = StatusEmprestimo.DEVOLVIDO;
    }

    public void marcarComoAtrasado() { if (status == StatusEmprestimo.ATIVO) status = StatusEmprestimo.ATRASADO; }

    // GETTERS
    public String getId() { return id; }
    public Livro getLivro() { return livro; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public double getMulta() { return multa; }
    public StatusEmprestimo getStatus() { return status; }

}
