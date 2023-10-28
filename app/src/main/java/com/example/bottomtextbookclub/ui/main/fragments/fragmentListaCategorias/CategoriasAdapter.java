package com.example.bottomtextbookclub.ui.main.fragments.fragmentListaCategorias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bottomtextbookclub.R;
import com.example.bottomtextbookclub.data.model.negocio.dominio.Categorias;
import java.util.ArrayList;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Categorias> categoriasList;
    private OnCategoriaClickListener onCategoriaClickListener;

    public CategoriasAdapter(ArrayList<Categorias> categoriasList, OnCategoriaClickListener listener) {
        this.categoriasList = categoriasList;
        this.onCategoriaClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoriaView = inflater.inflate(R.layout.categoria_item, parent, false);

        //categoriaView.setOnClickListener(new myOnClickListener());
        return new ViewHolder(categoriaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categorias categoria = categoriasList.get(position);
        holder.textNombreCategoria.setText(getNombreCategoria(categoria));
        holder.imageViewCategoria.setImageResource(getImgCategoria(categoria));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("se hizo click en CategoriasAdapter;onbindviewholder");
                if (onCategoriaClickListener != null) {
                    onCategoriaClickListener.onCategoriaClick(categoria);
                }
            }
        });
    }

    private String getNombreCategoria(Categorias categoria) {
        String nombreCategoria = context.getString(context.getResources().getIdentifier(categoria.toString(), "string", context.getPackageName()));
        return nombreCategoria;
    }

    private int getImgCategoria(Categorias categoria) {
        int imageResource = context.getResources().getIdentifier(categoria.getNombre(), "drawable", context.getPackageName());
        return imageResource;
    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    public interface OnCategoriaClickListener {
        void onCategoriaClick(Categorias categoria);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textNombreCategoria;
        public ImageView imageViewCategoria;

        public ViewHolder(View itemView) {
            super(itemView);
            textNombreCategoria = itemView.findViewById(R.id.textNombreCategoria);
            imageViewCategoria = itemView.findViewById(R.id.imageViewCategoria);

            itemView.setOnClickListener((View.OnClickListener) this);
        }

      @Override
        public void onClick(View view) {
            System.out.println("se ha pulsado el viewholder");
      }
            int position = getLayoutPosition(); // gets item position


    }
}
