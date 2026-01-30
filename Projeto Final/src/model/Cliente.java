package model;

public class Cliente {

    public static final int LIMITE_EMPRESTIMOS = 5;

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private boolean ativo;
    private int emprestimosAtivos;

    public Cliente(String nome, String cpf, String endereco, String telefone, String email) {

        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.ativo = true;
        this.emprestimosAtivos = 0;
    }

    public void registrarEmprestimo() {  emprestimosAtivos++; }
    public void registrarDevolucao() {
        if (emprestimosAtivos > 0) {
            emprestimosAtivos--;
        }
    }

    public void bloquear() { ativo = false; }
    public void ativar() { ativo = true; }

    // GETTERS
    public String getNome() {  return nome; }
    public String getCpf() { return cpf; }
    public String getEndereco() {  return endereco; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public boolean isAtivo() { return ativo; }
    public int getEmprestimosAtivos() { return emprestimosAtivos; }

}
