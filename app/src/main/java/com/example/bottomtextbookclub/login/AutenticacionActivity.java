package com.example.bottomtextbookclub.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bottomtextbookclub.MyApplication;
import com.example.bottomtextbookclub.utils.LocaleHelper;
import com.example.bottomtextbookclub.MainActivity;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentLang.DialogFragmentLang;

public class AutenticacionActivity extends AppCompatActivity implements DialogFragmentConfrirmar.ConfirmacionDialogListener, DialogFragmentLang.OnLanguageSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication myApplication = (MyApplication) this.getApplication();
        myApplication.logoutUser();

        setContentView(R.layout.activity_autenticacion);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commit();
        }
    }


    //-------------------------------------------idioma

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.wrapContext(newBase));
    }
    void cambiarIdioma(){showDialogFragmentLang(getSupportFragmentManager());}


    public static void showDialogFragmentLang(FragmentManager fragmentManager) {
        DialogFragmentLang dialogFragment = new DialogFragmentLang();
        dialogFragment.show(fragmentManager, "dialog_fragment_lang");
    }

    @Override
    public void onLanguageSelected(String languageCode) {

        LocaleHelper.setLocale(this, languageCode);

        recreate();

    }
//--------------------------------------   utils
    public static String getStringResourceByName(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "string", context.getPackageName());
        if (resId != 0) {
            return context.getString(resId);
        }
        return "";
    }

    private void registrar() {
        // Obtener el fragmento activo
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        // Verificar si el fragmento es de tipo RegistroFragment
        if (fragment instanceof RegistroFragment) {
            RegistroFragment registroFragment = (RegistroFragment) fragment;
            registroFragment.registrar();



            String nombreUsuario = registroFragment.getNombreUsuario();
            String password = registroFragment.getPassword();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance(nombreUsuario, password))
                    .commit();

        }
    }

    protected void nuke(){
        String stringNuke = (String)getString(R.string.nukeConfirmQuestion);

        showConfirmationDialog(stringNuke);}

    //------------------------------------------------ flujo
    @Override
    public void onBackPressed() {
        showConfirmationDialog();
    }
    private void volverOcerrar(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            //  Intent intent = new Intent(this, LoginScreen.class);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            getSupportFragmentManager().popBackStack();

        }}


    protected void showConfirmationDialog(){
        DialogFragmentConfrirmar.newInstance(getString(R.string.previous))
                .show(getSupportFragmentManager(), "confirmacionDialog");
    }
    protected void showConfirmationDialog(String text){
        DialogFragmentConfrirmar.newInstance(text)
                .show(getSupportFragmentManager(), "confirmacionDialog");

    }
    @Override
    public void onResultDialogConfirmacion(boolean result, String texto) {
        if (result && getString(R.string.nukeConfirmQuestion).equals(texto)) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                // Verificar si el fragmento es de tipo RegistroFragment
                if (fragment instanceof LoginFragment) {
                    LoginFragment loginFragment = (LoginFragment) fragment;
                    loginFragment.nuke();
            }
        }

        if (result && getString(R.string.previous).equals(texto)) {
            volverOcerrar();
        }
        String textoRegistrar = (String)getString(R.string.registrar_pregunta);
         if (result && textoRegistrar.equals(texto)) {
            registrar();
        }

    }
}
