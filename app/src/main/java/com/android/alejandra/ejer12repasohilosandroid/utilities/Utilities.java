package com.android.alejandra.ejer12repasohilosandroid.utilities;

/**
 * Created by aleja on 06/12/2017.
 */

public class Utilities {


    /**
     * Mëtodo que obtiene un String de un array de enteros que se le pasa
     *
     * @param numeros array de enteros que se debe pasar a String
     * @return el String que representa el array de numeros. Los número se separan por "-"
     */
    public static String getStringFromArrayInt(Integer[] numeros) {
        String textoArrayNumeros = "";
        for (int i = 0; i < numeros.length; i++) {
            textoArrayNumeros += numeros[i];
            if (i != numeros.length - 1) {
                //no es el último número
                textoArrayNumeros += "-";
            }

        }
        return textoArrayNumeros;
    }
}
