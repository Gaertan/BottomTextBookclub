package com.example.bottomtextbookclub.data.model;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bottomtextbookclub.LocaleHelper;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.Libros;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentLang.DialogFragmentLang;

import java.util.Locale;

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

    }
    public void init(String username){
        Libros libros = Libros.getInstancia();
        libros.init();

        loginUser(username);


    }
//-------------------------------------------- usuario

    public boolean isUserLoggedIn() {
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

    public void showDialogFragmentLang(FragmentManager fragmentManager) {
        DialogFragmentLang dialogFragment = new DialogFragmentLang();
        dialogFragment.show(fragmentManager, "dialog_fragment_lang");
    }

    protected void showConfirmationDialog(){

    }

    @Override
    public void onResultDialogConfirmacion(boolean result, String texto) {
        if (result && getString(R.string.previous).equals(texto)) {

        }

    }



}












