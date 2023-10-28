package com.example.bottomtextbookclub.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bottomtextbookclub.utils.LocaleHelper;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.Libros;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentLang.DialogFragmentLang;
import com.example.bottomtextbookclub.ui.main.fragments.fragmentListaCategorias.CategoriasAdapter;
import com.example.bottomtextbookclub.ui.main.fragments.fragmentListaCategorias.NavegacionPrincipalFragmentCategorias;
import com.example.bottomtextbookclub.ui.main.fragments.fragmentDetailsLibro.DetailsLibroFragment;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentAbout.DialogAbout;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentScrollable.DialogScrollable;
import com.example.bottomtextbookclub.ui.main.fragments.fragmentListaLibros.LibroFragment;
import com.example.bottomtextbookclub.login.AutenticacionActivity;

import java.util.ArrayList;

public class NavegacionPrincipal extends AppCompatActivity implements  CategoriasAdapter.OnCategoriaClickListener,LibroFragment.OnLibroClickListener, DialogFragmentConfrirmar.ConfirmacionDialogListener, DialogFragmentLang.OnLanguageSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Libros libros = Libros.getInstancia();
        libros.init();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, NavegacionPrincipalFragmentCategorias.newInstance())
                    .commitNow();
        }

        Button buttonVolver = findViewById(R.id.buttonVolverNavegacion);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }});




        Button buttonLang = findViewById(R.id.button_navegacion_principal_lang);
        buttonLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarIdioma();
            }});


    }







    //-------------------------------------------idioma
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.wrapContext(newBase));
    }
    private void cambiarIdioma(){showDialogFragmentLang(getSupportFragmentManager());}


    public void showDialogFragmentLang(FragmentManager fragmentManager) {
        DialogFragmentLang dialogFragment = new DialogFragmentLang();
        dialogFragment.show(fragmentManager, "dialog_fragment_lang");
    }

    @Override
    public void onLanguageSelected(String languageCode) {

        LocaleHelper.setLocale(this, languageCode);

        recreate();

    }
    //-------------------------------------------flujo
    @Override
    public void onResultDialogConfirmacion(boolean result, String texto) {
        if (result && getString(R.string.previous).equals(texto)) {
            volverOcerrar();
        }
        if (result && getString(R.string.restartQuestion).equals(texto)) {
            volverOcerrar();
        }
        }
    @Override
    public void onBackPressed() {
        showConfirmationDialog();
    }
    private void volverOcerrar(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
      //  Intent intent = new Intent(this, LoginScreen.class);
            Intent intent = new Intent(this, AutenticacionActivity.class);
        startActivity(intent);
        finish();
    } else {
        getSupportFragmentManager().popBackStack();
    }}


    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewCategorias);
            int itemPosition = recyclerView.indexOfChild(v);
            Log.e("Clicked and Position is ",String.valueOf(itemPosition));
        }
    }



    //-------------------------------------------menús
    protected void showConfirmationDialog(){
        DialogFragmentConfrirmar.newInstance(getString(R.string.previous))
                .show(getSupportFragmentManager(), "confirmacionDialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id ==  R.id.menu_acerca_de){
            showScrollDialogAbout();
            Toast.makeText(this,
                    "Has pulsado la opción acerca_de", Toast.LENGTH_SHORT).show();

            return true;
        }
        if(id ==  R.id.menu_ayuda){
            Toast.makeText(this,
                    "Has pulsado la opción ayuda", Toast.LENGTH_SHORT).show();
            showScrollDialogAyuda();

            return true;
        }

        else if(id ==  R.id.menu_historial){
            Toast.makeText(this,
                    "Has pulsado la opción historial", Toast.LENGTH_SHORT).show();
            showDialogHistorial();
            return true;
        }
        return false;
    }
    private void showScrollDialogAyuda() {
        FragmentManager fm = getSupportFragmentManager();
        DialogScrollable dialog = DialogScrollable.newInstance("");
        dialog.show(fm, "fragment_scroll_dialog");
    }
    private void showScrollDialogAbout() {
        FragmentManager fm = getSupportFragmentManager();
        DialogAbout dialog = DialogAbout.newInstance("");
        dialog.show(fm, "fragment_about_dialog");
    }
    private void showDialogHistorial() {
        //por implementar
    }


    //-------------------------------------------navegacion fragmento


    @Override
    public void onCategoriaClick(Categorias categoria) {
        // Handle the category click in your activity
        Libros libros = Libros.getInstancia();
        ArrayList<Libro> listaLibros = libros.getLibrosPorCategoria(categoria);
        LibroFragment libroFragment = LibroFragment.newInstance(3);
        libroFragment.actualizarListaLibros(listaLibros);
        cambiarFragmento(libroFragment);
    }
    @Override
    public void onLibroClick(Libro libro) {
        DetailsLibroFragment detailsLibroFragment = DetailsLibroFragment.newInstance(libro);
        System.out.println("cambiar fragmento a libro" );
        cambiarFragmento(detailsLibroFragment);
    }

    public void cambiarFragmento(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }



}