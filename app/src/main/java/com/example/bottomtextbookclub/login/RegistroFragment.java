package com.example.bottomtextbookclub.login;


import android.accounts.AccountManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.data.model.negocio.dominio.UsuarioAccount;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


import com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar.DialogFragmentConfrirmar;


public class RegistroFragment extends Fragment implements DialogFragmentConfrirmar.ConfirmacionDialogListener {
    private EditText editTextUsuario;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private Spinner spinnerCategoria;
    private  Button registroButton;
    private DialogFragmentConfrirmar dialogFragmentConfrirmar;
    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }
    public static RegistroFragment newInstance(String usuario, String password) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString("nombreUsuario", usuario);
        args.putString("password", password);
        fragment.setArguments(args);
        return fragment;

    }
    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Verifica las condiciones y habilita o deshabilita el botón de registro
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextPassword2.getText().toString();
            String userName = editTextUsuario.getText().toString();

            boolean isValid = !password.isEmpty() && password.length() >= 3 && password.equals(confirmPassword) && userName.length()>=3;

            registroButton.setEnabled(isValid);
        }
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        editTextUsuario = view.findViewById(R.id.loginUsuarioFragmentregistro);
        registroButton = view.findViewById(R.id.buttonRegistrarseFragmentregistro);

        editTextPassword = view.findViewById(R.id.loginPasswordFragmentregistro);
        editTextPassword2 = view.findViewById(R.id.loginPasswordFragmentregistro2);

        editTextUsuario.addTextChangedListener(passwordTextWatcher);
        editTextPassword.addTextChangedListener(passwordTextWatcher);
        editTextPassword2.addTextChangedListener(passwordTextWatcher);


        spinnerCategoria = view.findViewById(R.id.spinnerCategoriaFragmentregistro);
        /*        ArrayAdapter<Categorias> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Categorias.values()

        );*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                (getContext()),
                android.R.layout.simple_spinner_item,
                obtenerNombresCategorias()
        );
        spinnerCategoria.setAdapter(adapter);



        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = (String)getString(R.string.registrar_pregunta);
               showConfirmationDialog(texto);
            }
        });


        Button buttonVolver =  view.findViewById(R.id.buttonVolverFragmentoRegister);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = (String)getString(R.string.previous);
                showConfirmationDialog(texto);
            }});

        TransitionInflater transInflater = TransitionInflater.from(requireContext());
        setExitTransition(transInflater.inflateTransition(R.transition.slide_right));

        return view;
    }


    public String getNombreUsuario() {
        return editTextUsuario.getText().toString();
    }

    public String getPassword() {
        return editTextPassword.getText().toString();
    }



   protected void showConfirmationDialog(String texto){
        this.dialogFragmentConfrirmar =  DialogFragmentConfrirmar.newInstance(texto);
        //dialogFragmentConfrirmar.setTargetFragment(this, 300);

        dialogFragmentConfrirmar.show(getActivity().getSupportFragmentManager(), "confirmacionDialog");
    }
   public void registrar() {
        String nombreUsuario = editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        try {
            AccountManager accountManager = AccountManager.get(requireContext());
            System.out.println(nombreUsuario);
            UsuarioAccount.guardarCuentaCredenciales(accountManager, nombreUsuario, password);
            UsuarioAccount.setCategoriaFavorita(accountManager, nombreUsuario, spinnerCategoria.getSelectedItem().toString());
            if (UsuarioAccount.validarCredenciales(accountManager, nombreUsuario, password)) {
                Toast.makeText(getContext(), "Registro exitoso; el usuario se encuentra", Toast.LENGTH_LONG).show();
                devolverDatosLogin( nombreUsuario,password);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error en el registro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void devolverDatosLogin(String nombreUsuario, String password){
        Bundle data = new Bundle();
        data.putString("usuario", nombreUsuario);
        data.putString("password", password);
        getParentFragmentManager().setFragmentResult("registroExitoso", data);}

    private List<String> obtenerNombresCategorias() {
        List<String> nombresCategorias = new ArrayList<>();
        for (Categorias categoria : Categorias.values()) {

           String resource;
            resource = AutenticacionActivity.getStringResourceByName(getContext(),categoria.toString());
            nombresCategorias.add(resource);
        }
        return nombresCategorias;
    }



    public void onResultDialogConfirmacion(boolean resultado, String text) {
        Log.d("RegistroFragment", "Texto Registrar: " + getString(R.string.registrar_pregunta));
        String textoRegistrar = (String)getString(R.string.registrar_pregunta);
        Log.d("RegistroFragment", "Resultado: " + resultado + ", Texto: " + text);
        if (resultado && getString(R.string.previous).equals(text)) {
            volverOcerrar();
        }
        if (resultado) {
            volverOcerrar();
        }
        else if (resultado && textoRegistrar.equals(text)) {
            registrar();
        }
    }

    private void volverOcerrar() {
        getParentFragmentManager().popBackStack();

    }


}
