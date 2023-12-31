package com.example.bottomtextbookclub.login;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomtextbookclub.MyApplication;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.UsuariosAccount;
import com.example.bottomtextbookclub.ui.main.NavegacionPrincipal;

public class LoginFragment extends Fragment {
    protected EditText editTextUsuario;
    protected EditText editTextPassword;
    protected CheckBox checkBoxRecordar;
    protected Button loginButton;
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
        loginButton = view.findViewById(R.id.buttonLogin2Fragmentlogin);
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
                AutenticacionActivity activity = (AutenticacionActivity) requireActivity();
                activity.nuke();
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


        Button buttonLang = view.findViewById(R.id.button_login_lang);
        buttonLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutenticacionActivity activity = (AutenticacionActivity) getActivity();
                if (activity != null) {
                    activity.cambiarIdioma();
                }
            }});


        loginButton.setEnabled(false);

        editTextUsuario.addTextChangedListener(passwordTextWatcher);
        editTextPassword.addTextChangedListener(passwordTextWatcher);
        cargarCredenciales();



       // TransitionInflater transInflater = TransitionInflater.from(requireContext());



        return view;
    }


    @NonNull
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

            if (UsuariosAccount.validarCredenciales(accountManager, nombreUsuario, password)) {
                if (checkBoxRecordar.isChecked()) {
                    guardarCredenciales(nombreUsuario, password);
                }
                if (!checkBoxRecordar.isChecked()){
                    borrarCredenciales();
                }
                // Intent intent = new Intent(this, pantallaPrincipal.class);
                Intent intent = new Intent(getContext(), NavegacionPrincipal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                MyApplication myApplication = (MyApplication) requireActivity().getApplication();
                myApplication.loginUser(nombreUsuario);

                startActivity(intent);


            } else {
                Toast.makeText(getContext(), getString(R.string.login_failed), Toast.LENGTH_LONG).show();
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


    protected void nuke(){
        AccountManager accountManager = AccountManager.get(getActivity());

        try {
            UsuariosAccount.tabulaRasa(accountManager);
            Toast.makeText(getActivity(), getString(R.string.nukeConfirm), Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());}
    }


    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Verifica las condiciones y habilita o deshabilita el botón de registro


            String nombreUsuario = editTextUsuario.getText().toString();
            String password=editTextPassword.getText().toString();

            boolean isValid = !password.isEmpty() && password.length() >= 3 && nombreUsuario.length()>=3;

            loginButton.setEnabled(isValid);
        }
    };


}
