package util;

import model.Cliente;

public class ClienteUtil {

    public static String toLinha(Cliente cliente){
        return String.join(";", cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getTelefone(), cliente.getEmail(), String.valueOf(cliente.isAtivo()), String.valueOf(cliente.getEmprestimosAtivos()));
    }

    public static Cliente fromLinha(String linha){
        String [] p = linha.split(";");

        Cliente cliente = new Cliente(p[0], p[1], p[2], p[3], p[4]);
        if(!Boolean.parseBoolean(p[5])) cliente.bloquear();
        int ativos = Integer.parseInt(p[6]);
        for(int i = 0; i < ativos; i++){
            cliente.registrarEmprestimo();
        }
        return cliente;
    }

}
