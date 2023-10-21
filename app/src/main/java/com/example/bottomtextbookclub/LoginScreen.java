package com.example.bottomtextbookclub;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomtextbookclub.data.model.Categorias;
import com.example.bottomtextbookclub.data.model.UsuarioAccount;
import com.example.bottomtextbookclub.ui.main.MainViewModel;
import com.example.bottomtextbookclub.ui.placeholder.NavegacionPrincipal;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            CheckBox checkBoxRecordar = findViewById(R.id.checkBoxRecordar);
            EditText editTextUsuario = findViewById(R.id.loginUsuario);
            EditText editTextPassword = findViewById(R.id.loginPassword);
            Spinner spinner = findViewById(R.id.spinnerCategoria);
            setContentView(R.layout.layoutlogin);


            Button boton = findViewById(R.id.buttonLogin2);
            boton.setOnClickListener(this);
            Button botonProhibido = findViewById(R.id.buttonForbiddenButton);
            botonProhibido.setOnClickListener(this);

            Button buttonRegistrarse = findViewById(R.id.buttonRegistrarse);
            buttonRegistrarse.setOnClickListener(this);

            cargarCredenciales();

        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin2) {
            login();
        }

        if (v.getId() == R.id.buttonRegistrarse) {
            registrar();
        }
        if(v.getId()==R.id.buttonForbiddenButton){
             nuke();
        }

    }

    private void login(){



        try {
            CheckBox checkBoxRecordar = findViewById(R.id.checkBoxRecordar);
            EditText editTextUsuario = findViewById(R.id.loginUsuario);
            EditText editTextPassword = findViewById(R.id.loginPassword);
            String nombreUsuario = editTextUsuario.getText().toString();
            String password = editTextPassword.getText().toString();

            AccountManager accountManager = AccountManager.get(this);

            if (UsuarioAccount.validarCredenciales(accountManager, nombreUsuario, password)) {
                if (checkBoxRecordar.isChecked()) {
                    guardarCredenciales(nombreUsuario, password);
                }
                if (!checkBoxRecordar.isChecked()){
                    borrarCredenciales();
                }
                // Intent intent = new Intent(this, pantallaPrincipal.class);
                Intent intent = new Intent(this, NavegacionPrincipal.class);
                startActivity(intent);


            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}




    }


    private static final String PREFS_NAME = "LoginPrefs";

    private void guardarCredenciales(String nombreUsuario, String password) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nombreUsuario", nombreUsuario);
        editor.putString("password", password);
        editor.apply();
    }

    private void cargarCredenciales() {
            CheckBox checkBoxRecordar = findViewById(R.id.checkBoxRecordar);
            EditText editTextUsuario = findViewById(R.id.loginUsuario);
            EditText editTextPassword = findViewById(R.id.loginPassword);

        try {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

            String nombreUsuario = settings.getString("nombreUsuario", "");
            String password = settings.getString("password", "");

            if (nombreUsuario!=null && password!=null)  checkBoxRecordar.setChecked(true);
            else checkBoxRecordar.setChecked(false);
            editTextUsuario.setText(nombreUsuario);
            editTextPassword.setText(password);

        }catch (Exception e){

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println(e.getMessage());

        }

    }
    private void borrarCredenciales() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("nombreUsuario");
        editor.remove("password");
        editor.remove("recordar");
        editor.apply();
    }
    private void registrar() {
        Button boton = findViewById(R.id.buttonLogin2);
        Spinner spinner = findViewById(R.id.spinnerCategoria);

        if (boton.getVisibility()==View.VISIBLE) visibilidadRegistrar();
        else if (boton.getVisibility()!=View.VISIBLE){
        EditText editTextUsuario = findViewById(R.id.loginUsuario);
        EditText editTextPassword = findViewById(R.id.loginPassword);

        String nombreUsuario = editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        try {
            AccountManager accountManager = AccountManager.get(this);
            UsuarioAccount.guardarCuentaCredenciales(accountManager, nombreUsuario, password);
            UsuarioAccount.setCategoriaFavorita(accountManager,nombreUsuario,spinner.getSelectedItem().toString());
            if(UsuarioAccount.validarCredenciales(accountManager,nombreUsuario,password)){
                Toast.makeText(this, "Registro exitoso;el usuario se encuentra", Toast.LENGTH_SHORT).show();}


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en el registro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }}
    }
    private void visibilidadRegistrar() {

        CheckBox checkBoxRecordar = findViewById(R.id.checkBoxRecordar);
        checkBoxRecordar.setVisibility(View.GONE);

        Button boton = findViewById(R.id.buttonLogin2);
        boton.setVisibility(View.GONE);

        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerCategoria.setVisibility(View.VISIBLE);
        /*        ArrayAdapter<Categorias> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Categorias.values()

        );*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                obtenerNombresCategorias()
        );
        spinnerCategoria.setAdapter(adapter);


        TextView textCategoria = findViewById(R.id.textCategoria);
        textCategoria.setVisibility(View.VISIBLE);


        EditText editTextUsuario = findViewById(R.id.loginUsuario);
        editTextUsuario.setText("");

        EditText editTextPassword = findViewById(R.id.loginPassword);
        editTextPassword.setText("");
    }

    private List<String> obtenerNombresCategorias() {
        List<String> nombresCategorias = new ArrayList<>();
        for (Categorias categoria : Categorias.values()) {
            nombresCategorias.add(getStringResourceByName(categoria.toString()));
        }
        return nombresCategorias;
    }
    private String getStringResourceByName(String name) {
        int resId = getResources().getIdentifier(name, "string", getPackageName());
        if (resId != 0) {
            return getString(resId);
        }
        return "";
    }





    private void nuke(){
        AccountManager accountManager = AccountManager.get(this);

        try { UsuarioAccount.tabulaRasa(accountManager);
            Toast.makeText(this, "todos los usuarios han sido eliminados", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}
    }
}

