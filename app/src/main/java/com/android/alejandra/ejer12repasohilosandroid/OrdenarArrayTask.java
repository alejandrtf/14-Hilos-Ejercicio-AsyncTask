package com.android.alejandra.ejer12repasohilosandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alejandra.ejer12repasohilosandroid.utilities.Utilities;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by educastur on 03/02/2016.
 */
public class OrdenarArrayTask extends AsyncTask<Integer, Float, Integer[]> {
    private Activity activity; //lo necesito para llamar a findViewById. Es el contexto

    private TextView tvProgreso;
    private Button btOrdenar, btCancelar;
    private TextView mensaje;
    private TextView numerosOrdenados;
    private boolean seguirOrdenando = true;

    //CONSTRUCTOR
    public OrdenarArrayTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        //obtengo referencias
        btOrdenar = (Button) activity.findViewById(R.id.btOrdenar);
        btCancelar = (Button) activity.findViewById(R.id.btCancelar);
        mensaje = (TextView) activity.findViewById(R.id.tvMensajesInfoProceso);
        tvProgreso = (TextView) activity.findViewById(R.id.tvProgreso);
        numerosOrdenados = (TextView) activity.findViewById(R.id.tvArrayOrdenado);

        //desactivo el botón ordenar
        btOrdenar.setEnabled(false);
        //activo botón cancelar
        btCancelar.setEnabled(true);
        //Cambio texView informando que está ordenando
        mensaje.setText(R.string.ordenando);


    }

    @Override
    protected Integer[] doInBackground(Integer... arrayNumeros) {

        int aux;
        float porcentaje;

        //arrayNumeros entra sin ordenar
        for (int i = 0; i < arrayNumeros.length - 1; i++) {
            if (seguirOrdenando) {
                for (int j = 0; j < arrayNumeros.length - 1; j++) {
                    if (arrayNumeros[j] > arrayNumeros[j + 1]) {
                        aux = arrayNumeros[j];
                        arrayNumeros[j] = arrayNumeros[j + 1];
                        arrayNumeros[j + 1] = aux;

                    }
                }
            }
            //uso este Thread.sleep() para ralentizar un poco la ordenación y poder probar el botón cancelar.
            //Si lo quito la ordenación es tan rápida que no me da tiempo a pulsar cancelar.
            try {
                Thread.sleep(1, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //actualizo el porcentaje de ordenacion
            porcentaje = (((float) (i + 1)) / (arrayNumeros.length - 1)) * 100;
            publishProgress(porcentaje);


        }

        return arrayNumeros;  //ya ordenado
    }


    @Override
    protected void onProgressUpdate(Float... incremento) {
        tvProgreso.setText(String.format(" %.2f %%", incremento[0]));

    }


    @Override
    protected void onPostExecute(Integer[] arrayIntOrdenado) {
        // desactivo el botón cancelar
        btCancelar.setEnabled(false);
        //activo botón ordenar
        btOrdenar.setEnabled(true);
        //muestro Toast
        Toast.makeText(activity, "Números ordenados!!!", Toast.LENGTH_SHORT).show();
        //Cambio texView informando que ya se ordenó
        mensaje.setText(R.string.ordenado);
        //Muestro el array ordenado
        numerosOrdenados.setText(Utilities.getStringFromArrayInt(arrayIntOrdenado));
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
        seguirOrdenando = false;
    }


}
