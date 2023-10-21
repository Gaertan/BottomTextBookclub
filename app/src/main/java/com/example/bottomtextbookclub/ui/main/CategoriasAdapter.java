package com.example.bottomtextbookclub.ui.main;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bottomtextbookclub.data.model.Categorias;


import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.Categorias;

import java.util.ArrayList;
import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {
    Context context;

    private ArrayList<Categorias> categoriasList;

    public CategoriasAdapter(ArrayList<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View categoriaView = inflater.inflate(R.layout.categoria_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(categoriaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorias categoria = categoriasList.get(position);

        // Obtener el nombre de la categoría desde los recursos de strings
        String nombreCategoria = context.getString(context.getResources().getIdentifier(categoria.toString(), "string", context.getPackageName()));
        // Establecer el texto en el TextView
        holder.textNombreCategoria.setText(nombreCategoria);


        //String nombreImagen = categoria.getNombre() +".png";
        //  int imageResource = context.getResources().getIdentifier(nombreImagen, "drawable", context.getPackageName());
        int imageResource = context.getResources().getIdentifier(categoria.getNombre() , "drawable" ,
                context.getPackageName()) ;
        holder.imageViewCategoria.setImageResource(imageResource);
        System.out.println("el img resource es "+imageResource);
      //  Log.d("CategoriasAdapter", "Nombre de la imagen: " + nombreImagen);
      //  Log.d("CategoriasAdapter", "ID de la imagen: " + imageResource);

    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textNombreCategoria;
        public ImageView imageViewCategoria;

        public ViewHolder(View itemView) {
            super(itemView);

            textNombreCategoria = itemView.findViewById(R.id.textNombreCategoria);
            imageViewCategoria = itemView.findViewById(R.id.imageViewCategoria);
        }
    }

    private String getStringResourceByName(String name) {
        int resId = context.getResources().getIdentifier(name, "string", context.getPackageName());
        if (resId != 0) {
            return context.getString(resId);
        }
        return "";
    }
}
