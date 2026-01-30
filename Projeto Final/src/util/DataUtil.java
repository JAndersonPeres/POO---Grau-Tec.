package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

public class DataUtil {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatar(LocalDate data){
        return data == null ? "" : data.format(FORMATO);
    }

    public static LocalDate parse(String texto){
        return texto == null || texto.isBlank() ? null : LocalDate.parse(texto, FORMATO);
    }

}
