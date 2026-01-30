package service;

import model.Livro;
import java.util.*;

import exceptions.LivroNaoEncontradoException;

public class LivroService {

    private final List<Livro> livros;

    public LivroService(List<Livro> livros) { this.livros = livros; }

    public void cadastrar(Livro livro){ livros.add(livro); }

    public Livro buscarPorIsbn(String isbn){
        for (Livro l : livros){
            if(l.getIsbn().toLowerCase().equals(isbn.toLowerCase())) return l;
        }
        throw new LivroNaoEncontradoException();
    }

    public List<Livro> buscarPorTitulo(String filtro){
        List<Livro> filtragem = new ArrayList<>();
        for(Livro l : livros){
            if(l.getTitulo().toLowerCase().contains(filtro.toLowerCase())) filtragem.add(l);
        }
        if(filtragem.isEmpty()) throw new LivroNaoEncontradoException();
        return filtragem;
    }

    public List<Livro> buscarPorAutor(String filtro){
        List<Livro> filtragem = new ArrayList<>();
        for(Livro l : livros){
            if(l.getAutor().toLowerCase().contains(filtro.toLowerCase())) filtragem.add(l);
        }
        if(filtragem.isEmpty()) throw new LivroNaoEncontradoException();
        return filtragem;
    }

    public List<Livro> buscarPorEditora(String filtro){
        List<Livro> filtragem = new ArrayList<>();
        for(Livro l : livros){
            if(l.getEditora().toLowerCase().contains(filtro.toLowerCase())) filtragem.add(l);
        }
        if(filtragem.isEmpty()) throw new LivroNaoEncontradoException();
        return filtragem;
    }

    public List<Livro> buscarPorAno(int filtro){
        List<Livro> filtragem = new ArrayList<>();
        for(Livro l : livros){
            if(l.getAnoPublicacao() == filtro) filtragem.add(l);
        }
        if(filtragem.isEmpty()) throw new LivroNaoEncontradoException();
        return filtragem;
    }

    public List<Livro> listarTodos(){ return List.copyOf(livros); }

    public List<Livro> listarDisponiveis(){
        List<Livro> filtragem = new ArrayList<>();
        for(Livro l : livros){
            if(l.isDisponivel()) filtragem.add(l);
        }
        if(filtragem.isEmpty()) throw new LivroNaoEncontradoException();
        return filtragem;
    }

}
