package model;

public class Livro {
    
    private String titulo;
    private String autor;
    private String isbn;
    private String editora;
    private int anoPublicacao;
    private boolean disponivel;
    private int totalEmprestimos;

    public Livro(String titulo, String autor, String isbn, String editora, int anoPublicacao) {

        if (anoPublicacao <= 0) {
            throw new IllegalArgumentException("Ano de publicação inválido");
        }

        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
        this.totalEmprestimos = 0;

    }

    public void emprestar() {
        this.disponivel = false;
        this.totalEmprestimos++;
    }

    public void devolver() { this.disponivel = true; }

    // GETTERS
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getEditora() { return editora; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public boolean isDisponivel() { return disponivel; }
    public int getTotalEmprestimos() { return totalEmprestimos; }

}
