package com.example.bottomtextbookclub;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

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
    public String getSelectedLanguage() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(SELECTED_LANGUAGE, null);
    }


    @Override
    public void onResultDialogConfirmacion(boolean result, String texto) {
        if (result && getString(R.string.previous).equals(texto)) {

        }

    }

    public void promptRestart(DialogFragmentLang.OnLanguageSelectedListener onLanguageSelectedListener){
        System.out.println("se entra en promptrestart de MyApplication");
        Activity receivedActivity = (Activity) onLanguageSelectedListener;


      //  Intent intent = receivedActivity.getIntent();
        receivedActivity.finishAndRemoveTask();
        receivedActivity.recreate();


    }
















}












