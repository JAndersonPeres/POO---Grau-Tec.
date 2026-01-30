package util;

import model.*;

public class EmprestimoUtil {

    public static String toLinha(Emprestimo e){
        return String.join(";", e.getId(), e.getLivro().getIsbn(), e.getCliente().getCpf(), DataUtil.formatar(e.getDataEmprestimo()), DataUtil.formatar(e.getDataPrevistaDevolucao()), DataUtil.formatar(e.getDataDevolucao()), e.getStatus().name(), String.valueOf(e.getMulta()));
    }

    public static Emprestimo fromLinha(String linha, Livro livro, Cliente cliente){
        String [] p = linha.split(";");

        Emprestimo e = new Emprestimo( p[0], livro, cliente, DataUtil.parse(p[3]), DataUtil.parse(p[4]));
        if(p[5] != null && !p[5].isBlank()){
            e.registrarDevolucao(DataUtil.parse(p[5]), Double.parseDouble(p[7]));
        }
        return e;
    }

}
