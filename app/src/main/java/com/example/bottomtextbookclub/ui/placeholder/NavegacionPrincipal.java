package com.example.bottomtextbookclub.ui.placeholder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.ui.main.NavegacionPrincipalFragmentCategorias;

public class NavegacionPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, NavegacionPrincipalFragmentCategorias.newInstance())
                    .commitNow();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id ==  R.id.menu_ayuda){
            Toast.makeText(this,
                    "Has pulsado la opción ayuda", Toast.LENGTH_SHORT).show();

            return true;
        }
        if(id ==  R.id.menu_acerca_de){
            Toast.makeText(this,
                    "Has pulsado la opción acerca_de", Toast.LENGTH_SHORT).show();

            return true;
        }
        if(id ==  R.id.menu_historial){
            Toast.makeText(this,
                    "Has pulsado la opción historial", Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }




}