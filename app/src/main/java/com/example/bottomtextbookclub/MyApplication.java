package com.example.bottomtextbookclub;

import static com.example.bottomtextbookclub.data.model.negocio.Libros.getInstancia;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.utils.AutenticadorService;
import com.example.bottomtextbookclub.utils.LocaleHelper;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.Libros;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentLang.DialogFragmentLang;

public class MyApplication extends Application implements DialogFragmentConfrirmar.ConfirmacionDialogListener {
    private static final String PREFS_NAME = "mainPrefs";
    private static final String SELECTED_LANGUAGE = "SelectedLanguage";
    private boolean isUserLoggedIn = false;
    private String loggedInUsername;
    private String selectedLanguage;
    @Override
    public void onCreate() {
        super.onCreate();
      //inicializa el idioma con el default del sistema
      //  selectedLanguage = Locale.getDefault().getLanguage();
      //  changeLanguage(selectedLanguage);
        Intent autenticadorServiceIntent = new Intent(this, AutenticadorService.class);
        startService(autenticadorServiceIntent);
    }

    public void init(String username){
        Libros libros = getInstancia();
        libros.init();

        loginUser(username);


    }

//-------------------------------------------- usuario

    public boolean isUserLoggedIn() {
        if(loggedInUsername==null||loggedInUsername=="")return false;
        return isUserLoggedIn;
    }

    public void loginUser(String username) {
        isUserLoggedIn = true;
        loggedInUsername = username;
    }

    public void logoutUser() {
        isUserLoggedIn = false;
        loggedInUsername = null;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }





//---------------------------------------- idioma
    public void cambiarLenguage(Context context,String language) {
        LocaleHelper.setLocale(context, language);
        Toast.makeText(getApplicationContext(),getString(R.string.restartMayBeRequired), Toast.LENGTH_LONG).show();
    }
    public String getSelectedLanguage() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(SELECTED_LANGUAGE, null);
    }


    @Override
    public void onResultDialogConfirmacion(boolean result, String texto) {
        if (result && getString(R.string.previous).equals(texto)) {

        }

    }

    public static void init(){
        //asegurarse de que se no es nulo
        //realmente usariamos el metodo que requiere usuario lanzando excepcion si nulo
        Libros libros =getInstancia();

        Libro libro1 = new Libro("9788493806125", Categorias.ACCION, "El quijote", "El quijote  asdoimqweormoim qwoiemrioqmrewfewfewgtwrswtgwetfesdgfewfewt32twe li3rnu li32 rnnt43i2l niewfn lies fnlisdfnj slgk o3it ñowmfosdimfoidsmf ew");
        Libro libro2 = new Libro("9788469640401", Categorias.ACCION, "Eisenhorn", "qweflqiwunfñaiunsdoinsaoisdaonisanioddnosainodsaioiwqonifioqñenfñoainsoñndksfñaodsinfañskdnsañodinqwñodinañorniaeñorinasdñonawoind");
        Libro libro3 = new Libro("9788445004289", Categorias.ACCION, "Asterix el utlimo libro", "ryrsysefdqwaeqewtweasdqwaraflqiwunfñaiunsdoinsaoisdaonisanioddnosainodsaioiwqonifioqñenfñoainsoñndksfñaodsinfañskdnsañodinqwñodinañorniaeñorinasdñonawoind");

        libro1.setCategoria(Categorias.DEFAULT);
        libro2.setCategoria(Categorias.DEFAULT);
        libro3.setCategoria(Categorias.DEFAULT);
        libros.addLibro(libro1);
        libros.addLibro(libro2);
        libros.addLibro(libro3);



    }











}












