package com.android.alejandra.ejer12repasohilosandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alejandra.ejer12repasohilosandroid.utilities.Utilities;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_NUMEROS = 200;
    private Integer[] numeros;
    private TextView numerosDesordenados;
    private TextView numerosOrdenados;
    private Button btOrdenar, btCancelar;
    private TextView mensaje;
    OrdenarArrayTask ordenarArrayTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REFERENCIAS A  OBJETOS
        numerosDesordenados = (TextView) findViewById(R.id.tvArrayDesordenado);
        numerosOrdenados = (TextView) findViewById(R.id.tvArrayOrdenado);
        btOrdenar = (Button) findViewById(R.id.btOrdenar);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        mensaje = (TextView) findViewById(R.id.tvMensajesInfoProceso);


        //generar array de números desordenados entre 0 y 100
        generarNumerosDesordenados(MAX_NUMEROS);

        //mostrar numeros en textview
        numerosDesordenados.setText(Utilities.getStringFromArrayInt(numeros));

        //BOTÓN ORDENAR
        btOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ordeno el array
                (ordenarArrayTask = new OrdenarArrayTask(MainActivity.this)).execute(numeros);

            }
        });

        //BOTÓN CANCELAR
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //desactivo el botón cancelar
                btCancelar.setEnabled(false);
                //activo botón ordenar
                btOrdenar.setEnabled(true);
                //Cambio texView informando que se ha cancelado
                mensaje.setText(R.string.cancelado);
                //cancelo la ordenación
                ordenarArrayTask.cancel(true);
            }
        });

    }


    private void generarNumerosDesordenados(int max) {
        Random r = new Random();
        this.numeros = new Integer[max];


        for (int i = 0; i < max; i++) {
            this.numeros[i] = r.nextInt(100);
        }
    }
}
