package com.donje.utilies.utils;

import com.donje.utilies.exceptions.TelefoneInvalidoException;

import static com.donje.utilies.exceptions.MessagesExce.*;

public class Utilis {

    private Utilis(){
        throw new RuntimeException(INVALID_INSTANCE);
    }

    public static <T> T verificaNuloSeNaoEx(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException(OBJETO_NULO);
        }
        return obj;
    }


    private static String formatPhone(String telefone) throws TelefoneInvalidoException {
        // Remove todos os caracteres que não são dígitos
        String cleanedTelefone = telefone.replaceAll("[^\\d]", "");


        if (cleanedTelefone.length() == 11) {
            String formattedTelefone = String.format("(%s)%s-%s",
                    cleanedTelefone.substring(0, 2),
                    cleanedTelefone.substring(2, 7),
                    cleanedTelefone.substring(7));
            return validateAndReturn(formattedTelefone);
        } else if (cleanedTelefone.length() == 10) { // Exemplo: 1194842684
            String formattedTelefone = String.format("(%s)%s-%s",
                    cleanedTelefone.substring(0, 2),
                    cleanedTelefone.substring(2, 6),
                    cleanedTelefone.substring(6));
            return validateAndReturn(formattedTelefone);
        } else if (cleanedTelefone.length() == 12 && cleanedTelefone.startsWith("0")) {
            cleanedTelefone = cleanedTelefone.substring(1);
            String formattedTelefone = String.format("(%s)%s-%s",
                    cleanedTelefone.substring(0, 2),
                    cleanedTelefone.substring(2, 7),
                    cleanedTelefone.substring(7));
            return formattedTelefone;
        } else {
            throw new TelefoneInvalidoException(TELEFONE_INVALIDO);
        }
    }

    public static String validateAndReturn(String telefone) throws TelefoneInvalidoException {
      String telefoneFomated = formatPhone(telefone);
        String TELEFONE_PADRAO_COM_CINCO_DIGITOS = "\\(\\d{2}\\)\\d{5}-\\d{4}";
        String TELEFONE_PADRAO_COM_QUATRO_DIGITOS = "\\(\\d{2}\\)\\d{4}-\\d{4}";
        if (telefoneFomated.matches(TELEFONE_PADRAO_COM_CINCO_DIGITOS) ||
                telefoneFomated.matches(TELEFONE_PADRAO_COM_QUATRO_DIGITOS)) {
            return telefoneFomated;
        } else {
            throw new TelefoneInvalidoException(TELEFONE_INVALIDO);
        }
    }
}
