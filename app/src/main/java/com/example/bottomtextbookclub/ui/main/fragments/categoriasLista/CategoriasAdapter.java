package com.example.bottomtextbookclub.ui.main.fragments.categoriasLista;


import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomtextbookclub.R;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {
    Context context;
    private OnCategoriaClickListener onCategoriaClickListener;
    private ArrayList<Categorias> categoriasList;

    public CategoriasAdapter(ArrayList<Categorias> categoriasList, OnCategoriaClickListener listener) {
        this.categoriasList = categoriasList;
        this.onCategoriaClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View categoriaView = inflater.inflate(R.layout.categoria_item, parent, false);

        // Return a new holder instance

        ViewHolder viewHolder = new ViewHolder(categoriaView,onCategoriaClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorias categoria = categoriasList.get(position);


        holder.textNombreCategoria.setText(getNombreCategoria(categoria));


        //String nombreImagen = categoria.getNombre() +".png";
        //  int imageResource = context.getResources().getIdentifier(nombreImagen, "drawable", context.getPackageName());

        holder.imageViewCategoria.setImageResource(getImgCategoria(categoria));


    }

    private String getNombreCategoria(Categorias categoria) {
        // Obtener el nombre de la categoría desde los recursos de strings
        String nombreCategoria = context.getString(context.getResources().getIdentifier(categoria.toString(),
                "string", context.getPackageName()));
        return nombreCategoria;

    }

    private int getImgCategoria(Categorias categoria) {
        // Obtener el nombre de la categoría desde los recursos de strings
        int imageResource = context.getResources().getIdentifier(categoria.getNombre(), "drawable",
                context.getPackageName());
        System.out.println("el img resource es " + imageResource);
        //  Log.d("CategoriasAdapter", "Nombre de la imagen: " + nombreImagen);
        //  Log.d("CategoriasAdapter", "ID de la imagen: " + imageResource);
        return imageResource;

    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    //crear listener
    public interface OnCategoriaClickListener {
        void onCategoriaClick(Categorias categoria);
    }
    private OnCategoriaClickListener listener;
    public void setOnItemClickListener(OnCategoriaClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textNombreCategoria;
        public ImageView imageViewCategoria;
        private OnCategoriaClickListener listener;

        public ViewHolder(View itemView ,OnCategoriaClickListener listener ) {
            super(itemView);

            textNombreCategoria = itemView.findViewById(R.id.textNombreCategoria);
            imageViewCategoria = itemView.findViewById(R.id.imageViewCategoria);

            onCategoriaClickListener = listener;
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Categorias categoria = categoriasList.get(position);
                onCategoriaClickListener.onCategoriaClick(categoria);
            }
        }

    }
}
