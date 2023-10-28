package com.example.bottomtextbookclub.ui.main.fragments.fragmentDetailsLibro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;

public class DetailsLibroFragment extends Fragment {

    private static final String ARG_LIBRO = "arg_libro";
    private Libro libro;

    public DetailsLibroFragment() {
        // Required empty public constructor
    }

    public static DetailsLibroFragment newInstance(Libro libro) {
        DetailsLibroFragment fragment = new DetailsLibroFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIBRO, libro);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            libro = (Libro) getArguments().getSerializable(ARG_LIBRO);
        }

        TransitionInflater transInflater = TransitionInflater.from(requireContext());
        setExitTransition(transInflater.inflateTransition(R.transition.slide_right));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_item_details, container, false);

        // Inicializa los elementos de la vista
        TextView textViewTitulo = view.findViewById(R.id.textViewTituloLibroDetails);
        ImageView imageViewLibro = view.findViewById(R.id.imageViewLibroDetails);
        TextView textViewIsbn = view.findViewById(R.id.textViewIsbnLibroDetails);
        TextView textViewDescripcion = view.findViewById(R.id.textViewDescripcionLibroDetails);
        Button buttonComprar = view.findViewById(R.id.ButtonComprarLibroDetails);
        ScrollView scrollView = view.findViewById(R.id.scrollView4);

        // Asigna los valores del libro a los elementos de la vista
        textViewTitulo.setText(libro.getNombre());
        imageViewLibro.setImageResource(libro.getIdDeImagen(requireContext()));
        textViewIsbn.setText(libro.getIsbn());
        textViewDescripcion.setText(libro.getDescripcion());
        buttonComprar.setText(R.string.comprar);

        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarEnAmazon();
            }
        });
        return view;
    }


    private void buscarEnAmazon() {
        // Obtén el ISBN del libro
        String isbn = libro.getIsbn();

        // Crea una URL de búsqueda en Amazon usando el ISBN
        String amazonUrl = "https://www.amazon.com/s?k=" + isbn;

        // Abre el navegador con la URL de búsqueda
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(amazonUrl));
        startActivity(intent);
    }
}