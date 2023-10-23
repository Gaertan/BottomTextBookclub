package com.example.bottomtextbookclub.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.Libros;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.ui.main.fragments.categoriasLista.CategoriasAdapter;
import com.example.bottomtextbookclub.ui.main.fragments.categoriasLista.NavegacionPrincipalFragmentCategorias;
import com.example.bottomtextbookclub.ui.main.fragments.detailsLibro.DetailsLibroFragment;
import com.example.bottomtextbookclub.ui.main.fragments.dialogAboutFragment.DialogAbout;
import com.example.bottomtextbookclub.ui.main.fragments.dialogScrollableFragment.DialogScrollable;
import com.example.bottomtextbookclub.ui.main.fragments.librosLista.LibroFragment;
import com.example.bottomtextbookclub.ui.main.fragments.librosLista.LibroRecyclerViewAdapter;

import java.util.ArrayList;

public class NavegacionPrincipal extends AppCompatActivity implements CategoriasAdapter.OnCategoriaClickListener, LibroFragment.OnLibroClickListener {

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
                // Llama al método para cambiar el fragmento;
                // Esto vuelve al fragmento anterior en el stack
                getSupportFragmentManager().popBackStack();
            }});

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



    @Override
    public void onCategoriaClick(Categorias categoria) {
        Libros libros =Libros.getInstancia();
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