package com.example.fastfood;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;

public class Pedido extends AppCompatActivity {
    private TextView txtOrden;
    private ArrayList<Respuesta> respuest;
    private ImageView image;
    ImageView regresa;

    private static final String hamburguesa = "hamburguesa";
    private static final String pizza = "pizza";
    private static final String refresco = "refresco";
    private static final String papas = "papas fritas";
    private static final String emparedado = "emparedado";
    private static final String tacos = "tacos";


    private static final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);

        inicializar();


    }

    public  void regresar (){
        regresa = (ImageView) findViewById(R.id.imageView);
        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivityForResult(intent, 0);

            }
        });
    }

    public  void inicializar (){
        txtOrden = (TextView)findViewById(R.id.txtOrden);
        respuest = proveeerDatos();
        image = (ImageView)findViewById(R.id.imageView3);

        Intent intent2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent2.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent2.putExtra(RecognizerIntent.EXTRA_PROMPT, "¿QUE DESEAS ORDENAR?");
        try {
            startActivityForResult(intent2, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Lo sentimos no esta en el menú", Toast.LENGTH_SHORT).show();
        }
    }



    /////////////////////PARA
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK &&  requestCode == REQ_CODE_SPEECH_INPUT){
            ArrayList<String>reconocido = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String escuchado = reconocido.get(0);
            txtOrden.setText(escuchado);
            prepararRespuesta(escuchado);

            if(escuchado.equals(hamburguesa)){
                    image.setImageResource(R.drawable.hamburguesa);
            }
            if(escuchado.equals(tacos)){
                image.setImageResource(R.drawable.taco);
            }
            if(escuchado.equals(emparedado)){
                image.setImageResource(R.drawable.emparedado);
            }
            if(escuchado.equals(refresco)){
                image.setImageResource(R.drawable.refresco);
            }
            if(escuchado.equals(papas)){
                image.setImageResource(R.drawable.papas);
            }
            if(escuchado.equals(pizza)){
                image.setImageResource(R.drawable.pizza);
            }

        }
    }




    private void prepararRespuesta(String escuchado) {
        String normalizar = Normalizer.normalize(escuchado,Normalizer.Form.NFC);
        String sinTilde = normalizar.replaceAll("[^\\p{ASCII}]","");

        int resultado;
        String respuesta = respuest.get(0).getRespuesta();
        for (int i = 0; i < respuest.size(); i++) {
            resultado = sinTilde.indexOf(respuest.get(i).getCuestion());
            if(resultado != -1){
                respuesta = respuest.get(i).getRespuesta();
            }
        }

        responder (respuesta);

    }

    private void responder(String respuestita) {
        txtOrden.setText(respuestita);
    }

    public ArrayList<Respuesta> proveeerDatos(){
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(new Respuesta("defecto","NO ESTA EN EL MENU"));
        respuestas.add(new Respuesta("hamburguesa","$2.50"));
        respuestas.add(new Respuesta("pizza","$3.50"));
        respuestas.add(new Respuesta("refresco","$1.50"));
        respuestas.add(new Respuesta("tacos","$1.50"));
        respuestas.add(new Respuesta("emparedado","$2.50"));
        respuestas.add(new Respuesta("papas fritas","$2.00"));
        return respuestas;
    }


}
