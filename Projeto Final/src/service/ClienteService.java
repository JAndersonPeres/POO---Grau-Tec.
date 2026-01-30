package service;

import model.Cliente;
import java.util.*;

import exceptions.ClienteNaoEncontradoException;

public class ClienteService {

    private final List<Cliente> clientes;

    public ClienteService(List<Cliente> clientes) { this.clientes = clientes; }

    public void cadastrar(Cliente cliente) { this.clientes.add(cliente); }

    public Cliente buscarPorCpf(String cpf){
        for(Cliente c : clientes){
            if(c.getCpf().equals(cpf)) return c;
        }
        throw new ClienteNaoEncontradoException();
    }

    public void bloquearCliente(String cpf){
        buscarPorCpf(cpf).bloquear();
    }

    public void ativarCliente(String cpf){
        buscarPorCpf(cpf).ativar();
    }

    public List<Cliente> listarTodos(){ return List.copyOf(clientes); }

    public List<Cliente> listarAtivos(){
        List<Cliente> filtragem = new ArrayList<>();
        for(Cliente c : clientes){
            if(c.isAtivo()) filtragem.add(c);
        }
        if(filtragem.isEmpty()) throw new ClienteNaoEncontradoException();
        return filtragem;
    }

}
