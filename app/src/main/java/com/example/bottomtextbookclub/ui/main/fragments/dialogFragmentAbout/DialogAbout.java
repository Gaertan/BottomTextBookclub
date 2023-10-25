package com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentAbout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.bottomtextbookclub.R;

public class DialogAbout extends DialogFragment {


    private TextView autor;
    private TextView ciclo;
    private ImageButton imageButton;
    private Button botonVolver;

    public DialogAbout() {
    }

    public static DialogAbout newInstance(String title) {

        DialogAbout frag = new DialogAbout();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        Log.d("DialogAbout", "devolviendo isntancia");
        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DialogAbout", "onCreateView called");
        return inflater.inflate(R.layout.fragment_about_dialog2, container);

    }


    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get fields from view
        autor = view.findViewById(R.id.TextViewAboutNombre);
        ciclo = view.findViewById(R.id.TextViewAboutNombreCiclo);
        imageButton = view.findViewById(R.id.imageButtonAbout);
        botonVolver = view.findViewById(R.id.buttonDialogVolver);
        // Fetch arguments from bundle and set title

        String title = getArguments().getString("autor", "Gaertan");

        String autorText = getString(R.string.autor);
        String cicloText = getString(R.string.ciclo);


        int imageResource = getContext().getResources().getIdentifier("sonavowner", "drawable",
                getContext().getPackageName());

        getDialog().setTitle(title);
        autor.setText(autorText);
        ciclo.setText(cicloText);
        botonVolver.setText(getString(R.string.previous));
        imageButton.setImageResource(imageResource);

      //  Log.d("DialogAbout", "elementos añadidos");

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCurrentActivityAbout();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://linktr.ee/gaertan";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    private void closeCurrentActivityAbout() {
        // Cierra el fragmento actual
        dismiss();
    }
    }



