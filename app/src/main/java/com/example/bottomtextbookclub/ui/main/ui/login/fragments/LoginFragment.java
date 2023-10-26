package com.example.bottomtextbookclub.ui.main.ui.login.fragments;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.UsuarioAccount;
import com.example.bottomtextbookclub.ui.main.NavegacionPrincipal;

public class LoginFragment extends Fragment {
    protected EditText editTextUsuario;
    protected EditText editTextPassword;
    protected CheckBox checkBoxRecordar;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsuario = view.findViewById(R.id.loginUsuarioFragmentlogin);
        editTextPassword = view.findViewById(R.id.loginPasswordFragmentlogin);
        checkBoxRecordar = view.findViewById(R.id.checkBoxRecordarFragmentlogin);

        Button loginButton = view.findViewById(R.id.buttonLogin2Fragmentlogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Button nukeButton = view.findViewById(R.id.buttonForbiddenButtonFragmentlogin);
        nukeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuke();
            }
        });

        Button registroButton = view.findViewById(R.id.buttonRegistrarseFragmentlogin);
        String nombreUsuario = this.editTextUsuario.getText().toString();
        String password=this.editTextPassword.getText().toString();
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirRegistro(nombreUsuario,password);
            }
        });
        Button buttonVolver =  view.findViewById(R.id.buttonVolverFragmentoLogin);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }});



        cargarCredenciales();
        return view;
    }


    public static LoginFragment newInstance(String nombreUsuario, String password) {
        //empaqueta los campos para el viewcreated
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("nombreUsuario", nombreUsuario);
        args.putString("password", password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // datos desde la budle
       try {
           String nombreUsuario = getArguments().getString("nombreUsuario");
           String password = getArguments().getString("password");
           editTextUsuario.setText(nombreUsuario);
           editTextPassword.setText(password);
       }catch (Exception e){};




    }



    protected void abrirRegistro(String usuario, String password) {
        RegistroFragment registroFragment = RegistroFragment.newInstance(usuario,password);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.container, registroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void abrirRegistro() {
        RegistroFragment registroFragment = RegistroFragment.newInstance();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.container, registroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void login(){



        try {
            String nombreUsuario = editTextUsuario.getText().toString();
            String password = editTextPassword.getText().toString();

            AccountManager accountManager = AccountManager.get(getContext());

            if (UsuarioAccount.validarCredenciales(accountManager, nombreUsuario, password)) {
                if (checkBoxRecordar.isChecked()) {
                    guardarCredenciales(nombreUsuario, password);
                }
                if (!checkBoxRecordar.isChecked()){
                    borrarCredenciales();
                }
                // Intent intent = new Intent(this, pantallaPrincipal.class);
                Intent intent = new Intent(getContext(), NavegacionPrincipal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            } else {
                Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}




    }




    private static final String PREFS_NAME = "LoginPrefs";

    protected void guardarCredenciales(String nombreUsuario, String password) {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nombreUsuario", nombreUsuario);
        editor.putString("password", password);
        editor.apply();
    }

    protected void cargarCredenciales() {
        CheckBox checkBoxRecordar = getActivity().findViewById(R.id.checkBoxRecordarFragmentlogin);
        EditText editTextUsuario = getActivity().findViewById(R.id.loginUsuarioFragmentlogin);
        EditText editTextPassword = getActivity().findViewById(R.id.loginPasswordFragmentlogin);

        try {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);

            String nombreUsuario = settings.getString("nombreUsuario", "");
            String password = settings.getString("password", "");

            if (nombreUsuario!=null && password!=null)  this.checkBoxRecordar.setChecked(true);
            else this.checkBoxRecordar.setChecked(false);
            this.editTextUsuario.setText(nombreUsuario);
            this.editTextPassword.setText(password);

        }catch (Exception e){

            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());

        }

    }
    private void borrarCredenciales() {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("nombreUsuario");
        editor.remove("password");
        editor.remove("recordar");
        editor.apply();
    }


    private void nuke(){
        AccountManager accountManager = AccountManager.get(getActivity());

        try { UsuarioAccount.tabulaRasa(accountManager);
            Toast.makeText(getActivity(), "todos los usuarios han sido eliminados", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}
    }





}
