package com.example.bottomtextbookclub.ui.main.fragments.librosLista;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bottomtextbookclub.data.model.negocio.dominio.Libro;
import com.example.bottomtextbookclub.databinding.BookItemBinding;

import java.util.ArrayList;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class LibroRecyclerViewAdapter extends RecyclerView.Adapter<LibroRecyclerViewAdapter.ViewHolder> {

   // private final List<PlaceholderItem> mValues;
    private ArrayList<Libro> listaLibros;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(Libro libro);
    }
    public LibroRecyclerViewAdapter(ArrayList<Libro> items) {
        listaLibros = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.libroItem = listaLibros.get(position);
        holder.textViewTitulo.setText(listaLibros.get(position).getNombre());
        holder.textViewDescripcion.setText(listaLibros.get(position).getDescripcion());
        holder.textViewIsbn.setText(listaLibros.get(position).getIsbn());
        holder.imageViewLibro.setImageResource(
                listaLibros.get(position).getIdDeImagen(holder.itemView.getContext()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.libroItem);
                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return listaLibros.size();
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewTitulo;
        public final TextView textViewDescripcion;
        public final TextView textViewIsbn;
        public final ImageView imageViewLibro;
        public Libro libroItem;

        public ViewHolder(BookItemBinding binding) {
            super(binding.getRoot());

            textViewTitulo = binding.textViewTituloLibroCard;
            textViewDescripcion = binding.textViewDescripcionLibroCard;
            textViewIsbn = binding.textViewIsbnLibroCard;
            imageViewLibro = binding.imageViewLibroCard;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewDescripcion.getText() + "'";
        }
    }
}