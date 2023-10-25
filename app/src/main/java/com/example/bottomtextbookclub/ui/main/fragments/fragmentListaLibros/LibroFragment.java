package com.example.bottomtextbookclub.ui.main.fragments.fragmentListaLibros;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.ui.main.NavegacionPrincipal;
import com.example.bottomtextbookclub.ui.main.fragments.fragmentDetailsLibro.DetailsLibroFragment;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class LibroFragment extends Fragment implements LibroRecyclerViewAdapter.OnItemClickListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 3;
    private ArrayList<Libro> listaLibros;
    private LibroRecyclerViewAdapter adapter;
    private OnLibroClickListener mListener;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LibroFragment() {
    }

    // TODO: Customize parameter initialization

    public static LibroFragment newInstance(int columnCount) {
        LibroFragment fragment = new LibroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    public interface OnLibroClickListener {
        void onLibroClick(Libro libro);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //  mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnLibroClickListener) {
            mListener = (OnLibroClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ClickListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new LibroRecyclerViewAdapter(listaLibros);
            adapter.setOnItemClickListener((LibroRecyclerViewAdapter.OnItemClickListener) this); // Establecer el listener
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public void actualizarListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
        if (getView() != null) {
            RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
           recyclerView.setAdapter(new LibroRecyclerViewAdapter(listaLibros));
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemClick(Libro libro) {
        if (getActivity() instanceof NavegacionPrincipal) {
            ((NavegacionPrincipal) getActivity()).cambiarFragmento(DetailsLibroFragment.newInstance(libro));
        }
    }
}