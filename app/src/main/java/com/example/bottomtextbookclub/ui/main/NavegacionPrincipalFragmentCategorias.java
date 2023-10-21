package com.example.bottomtextbookclub.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.Categorias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NavegacionPrincipalFragmentCategorias extends Fragment {

    // Otras variables miembro

    public static NavegacionPrincipalFragmentCategorias newInstance() {
        return new NavegacionPrincipalFragmentCategorias();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navegacion_categorias, container, false);

        // Inicializa el RecyclerView y configura el adaptador
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ArrayList<Categorias> categoriasList = new ArrayList<>(Arrays.asList(Categorias.values()));
        CategoriasAdapter adapter = new CategoriasAdapter(categoriasList);
        recyclerView.setAdapter(adapter);

        // Resto del código

        return view;
    }
}
