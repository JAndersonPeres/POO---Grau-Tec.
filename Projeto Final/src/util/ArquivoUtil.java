package util;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import exceptions.PersistenciaException;

public class ArquivoUtil {

    public static List<String> lerLinhas(String caminho){
        try{
            if(!Files.exists(Path.of(caminho))) return List.of();
            return Files.readAllLines(Path.of(caminho));
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao ler arquivo: " + caminho);
        }
    }

    public static void escreverLinhas(String caminho, List<String> linhas){
        try{
            Files.write(Path.of(caminho), linhas);
        } catch (IOException e){
            throw new PersistenciaException("Erro ao escrever arquivo: " + caminho);
        }
    }

}
