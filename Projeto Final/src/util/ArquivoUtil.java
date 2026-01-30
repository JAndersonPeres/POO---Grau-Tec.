package util;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import exceptions.PersistenciaException;

public class ArquivoUtil {

    public static List<String> lerLinhas(String caminho){
        try{
            Path path = Paths.get(caminho);

            if(!Files.exists(path)){
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao ler arquivo: " + caminho);
        }
    }

    public static void escreverLinhas(String caminho, List<String> linhas){
        try{
            Path path = Paths.get(caminho);
            if(!Files.exists(path.getParent())){ Files.createDirectories(path.getParent());}
            Files.write(path, linhas, StandardOpenOption.CREATE , StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e){
            throw new PersistenciaException("Erro ao escrever arquivo: " + caminho);
        }
    }

}
