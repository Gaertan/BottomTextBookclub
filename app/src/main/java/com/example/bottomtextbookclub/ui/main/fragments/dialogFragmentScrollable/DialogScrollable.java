package com.example.bottomtextbookclub.ui.main.fragments.dialogFragmentScrollable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.bottomtextbookclub.R;

public class DialogScrollable extends DialogFragment {


    private TextView autor;
    private TextView textView;
    private ImageView imageView;
    private Button botonVolver;

    public DialogScrollable() {
    }

    public static DialogScrollable newInstance(String title) {

        DialogScrollable frag = new DialogScrollable();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scroll_dialog, container);

    }


    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get fields from view
        autor = view.findViewById(R.id.textViewAutorAbout);
        textView = view.findViewById(R.id.textViewScrollDialog);
        imageView = view.findViewById(R.id.imageViewScrollDialog);
        botonVolver = view.findViewById(R.id.buttoFragmentScrollAyudaVolver);
        // Fetch arguments from bundle and set title

        String title = getArguments().getString("autor", "Gaertan");
        String autorText = getString(R.string.autor);
        String lorem = getString(R.string.lorem_ipsum);

        int imageResource = getContext().getResources().getIdentifier("sonavowner", "drawable",
                getContext().getPackageName());
        getDialog().setTitle(title);
        autor.setText(autorText);
        textView.setText(lorem);
        imageView.setImageResource(imageResource);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad actual al presionar el botón
                closeCurrentActivity();
            }
        });


    }

    private void closeCurrentActivity() {
        dismiss();

    }

}













