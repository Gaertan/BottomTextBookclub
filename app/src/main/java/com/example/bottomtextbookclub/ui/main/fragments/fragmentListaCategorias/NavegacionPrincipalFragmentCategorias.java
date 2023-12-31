package com.example.bottomtextbookclub.ui.main.fragments.fragmentListaCategorias;

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
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import com.example.bottomtextbookclub.ui.main.NavegacionPrincipal;

import java.util.ArrayList;
import java.util.Arrays;


public class NavegacionPrincipalFragmentCategorias extends Fragment implements CategoriasAdapter.OnCategoriaClickListener  {
    CategoriasAdapter adapter;
    // Otras variables miembro

    public static NavegacionPrincipalFragmentCategorias newInstance() {
        return new NavegacionPrincipalFragmentCategorias();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navegacion_categorias, container, false);

        // Inicializa el RecyclerView y configura el adaptador
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCategorias);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ArrayList<Categorias> categoriasList = new ArrayList<>(Arrays.asList(Categorias.values()));
        CategoriasAdapter adapter = new CategoriasAdapter(categoriasList,this);
        this.adapter=adapter;

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCategoriaClick(Categorias categoria) {
        System.out.println("se entra en el metodo onCategoriasClien de navegacionprincipalfragmentcategorias" );
        if (getActivity() != null) {
            ((NavegacionPrincipal) getActivity()).onCategoriaClick(categoria);
        }

    }
    //interfaz de comunicacion con fragmento



    public void onResume() {

        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
