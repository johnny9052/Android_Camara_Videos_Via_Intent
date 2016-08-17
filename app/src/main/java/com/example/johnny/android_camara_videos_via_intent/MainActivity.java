package com.example.johnny.android_camara_videos_via_intent;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /*RECORDAR COLOCAR LOS PERMISOS EN EL MANIFIEST*/

    /*Elementos graficos*/
    private EditText txtNombreArchivo;
    private VideoView ViewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Referencia a elementos graficos*/
        txtNombreArchivo = (EditText) findViewById(R.id.txtNombreArchivo);
        ViewVideo = (VideoView) findViewById(R.id.videoView);
    }


    public void grabarVideo(View v) {
        /*Definimos el intent de tipo video*/
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        /*Creamos un archivo que contendra el video, mandanto el path (null es para indicar que no
        va en un directorio interno, solo en la raiz) ---- nombre del video*/
        /*RECORDAR QUE ESTE ARCHIVO QUEDA EN LA CARPETA ANDROID, EN LA CARPETA CON EL NOMBRE DEL
        * PAQUETE DEL PROYECTO, ESA ES LA RAIZ*/
        File video = new File(getExternalFilesDir(null), txtNombreArchivo.getText().toString());
        /*Mandamos el permiso para almacenar, y la ruta del video donde quedara*/
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(video));
        /*Ejecutamos la actividad*/
        startActivity(intent);
    }


    public void verVideo(View v) {
        /*Obtenemos la ruta del video, parandonos en la raiz + el nombre del video, y lo asiganmos
        * al view del video de la interfaz*/
        ViewVideo.setVideoURI(Uri.parse(getExternalFilesDir(null) + "/" + txtNombreArchivo.getText().toString()));
        /*Ejecutamos el video*/
        ViewVideo.start();
    }


}
