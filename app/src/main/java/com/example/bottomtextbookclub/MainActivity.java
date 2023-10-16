package com.example.bottomtextbookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Animation animSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creo la carpeta "anim" donde voy a definir que va a hacer cada animación
        animSplash = AnimationUtils.loadAnimation(this, R.anim.anim_splash);


        //uno cada elemento
        ImageView logo = findViewById(R.id.logo);
        TextView nombre = findViewById(R.id.textBottom);
        TextView slogan = findViewById(R.id.textProgramacion);

        Button boton = findViewById(R.id.buttonLogin);
        boton.setOnClickListener(this);

        //lanzo las animaciones
        logo.setAnimation(animSplash);
        slogan.setAnimation(animSplash);
        nombre.setAnimation(animSplash);
        boton.setAnimation(animSplash);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }
}