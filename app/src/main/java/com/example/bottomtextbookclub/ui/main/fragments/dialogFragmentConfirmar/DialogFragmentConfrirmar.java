package com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentConfirmar;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomtextbookclub.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogFragmentConfrirmar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogFragmentConfrirmar extends DialogFragment {
    ConfirmacionDialogListener confirmacionDialogListener;
    private TextView textviewprincipal;
    private TextView textviewPregunta;
    private Button buttonYes;
    private Button buttonNo;
    public DialogFragmentConfrirmar() {
        // Required empty public constructor
    }





    // TODO: Rename and change types and number of parameters
    public static DialogFragmentConfrirmar newInstance(String textoAMostrar) {
        DialogFragmentConfrirmar fragment = new DialogFragmentConfrirmar();
        Bundle args = new Bundle();
        args.putString("texto", textoAMostrar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_confrirmar, container, false);
    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get field from view

        textviewprincipal = (TextView) view.findViewById(R.id.textViewDialogConfirmarPrincipal);
        textviewPregunta = (TextView) view.findViewById(R.id.textViewDialogConfirmarPregunta);
        buttonNo = (Button) view.findViewById(R.id.buttonFragmentDialogConfirmarNo);
        buttonYes = (Button) view.findViewById(R.id.buttonFragmentDialogConfirmarOkay);
        // Fetch arguments from bundle and set title

        String texto = getArguments().getString("texto");

        textviewprincipal.setText(texto);
        textviewPregunta.setText(R.string.confirmQuestion);
        buttonNo.setText(R.string.cancel);
        buttonYes.setText(R.string.confirm);

        //on click listeners,devuelven true o false y cierran el dialogo
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacionDialogListener.onResultDialogConfirmacion(true, (String) textviewprincipal.getText());
                System.out.println("se ha pulsado onClick en el dialogFragmentconfirmar");
                dismiss();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacionDialogListener.onResultDialogConfirmacion(false, (String) textviewprincipal.getText());
                dismiss();
            }
        });
    }



    //ocurre antes del oncreate,bindea la clase escuchadora con esta
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ConfirmacionDialogListener) {
            confirmacionDialogListener = (ConfirmacionDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ConfirmacionDialogListener");
        }
    }


    public interface ConfirmacionDialogListener {
        void onResultDialogConfirmacion(boolean resultado, String text);

    }




/*

 //IMPLEMENTAR EN CLASE ESCUCHADORA;LA QUE LLAMA

// En método donde mostrar el diálogo de confirmación
private void onConfirmacionClick() {
    mostrarDialogoConfirmacion(getString(R.string.confirm_message));
}
// Método para mostrar el diálogo de confirmación
private void mostrarDialogoConfirmacion(String mensaje) {
 //Si es dentro de un Fragment, asegúrate de usar getParentFragmentManager() en lugar de getSupportFragmentManager().
    DialogFragmentConfrirmar.newInstance(mensaje)
            .show(getSupportFragmentManager(), "confirmacionDialog");
}

-----------------------------------------------
// Implementación de la interfaz ConfirmacionDialogListener, si solo resultado;
@Override
public void onResultDialogConfirmacion(boolean resultado) {
    if (resultado) {
        // Acciones a realizar cuando se confirma
        // Por ejemplo, crear un nuevo fragmento o ingresar un cliente etc

    } else {
        // (opcional)
    }
}
//IMPLEMENTACION OPCIONAL PARA VARIOS METODOS DE UNA MSMA ACTIVITY O METODO
    @Override
    public void onResultDialogConfirmacion(Boolean result,String texto) {
            ...
        if(result==true && texto.equals(otroTexto)){}
            }
            ...
    }

------------------------------------------

posible metodo para volver o cerrar ventanas

    private void volverOcerrar(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    } else {
        getSupportFragmentManager().popBackStack();

    }}


*/



}