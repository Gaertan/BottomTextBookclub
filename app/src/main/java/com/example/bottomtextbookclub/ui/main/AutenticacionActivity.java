package com.example.bottomtextbookclub.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bottomtextbookclub.MainActivity;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;
import com.example.bottomtextbookclub.ui.main.ui.login.fragments.LoginFragment;
import com.example.bottomtextbookclub.ui.main.ui.login.fragments.RegistroFragment;

public class AutenticacionActivity extends AppCompatActivity implements DialogFragmentConfrirmar.ConfirmacionDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacion);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commit();
        }
    }
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
        if (result && getString(R.string.previous).equals(texto)) {
            volverOcerrar();
        }
        Log.d("RegistroFragment", "Texto Registrar: " + getString(R.string.registrar_pregunta));
        String textoRegistrar = (String)getString(R.string.registrar_pregunta);
        Log.d("RegistroFragment", "Resultado: " + result + ", Texto: " + texto);
        if (result && getString(R.string.previous).equals(texto)) {
            volverOcerrar();
        }

        else if (result && textoRegistrar.equals(texto)) {
            registrar();
        }

    }
}