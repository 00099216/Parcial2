package com.andres00099216.parcial2.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public abstract  class NoticaAdapter extends RecyclerView.Adapter<NoticaAdapter.ViewHolder> {

    private Transformation transformation;
    private Context context;
    private List<NoticiaEnt> newList;


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView descripcion;
        ImageView imagen;
        ImageButton favorito;
        public ViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen_noticia);
            favorito = itemView.findViewById(R.id.boton_favoritos);
            descripcion= itemView.findViewById(R.id.descripcion_noticia);
            titulo  = itemView.findViewById(R.id.titulo_noticia);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticiaEnt newAux = newList.get(holder.getAdapterPosition());

        NoticiaEnt news = null;

        holder.titulo.setText(news.getNotTittle());
        holder.descripcion.setText(news.getNotDescription());

        if (!(news.getNotCoverImage() == null)) {
            Picasso.get().load(news.getNotCoverImage())
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.imagen);

        } else {
            Picasso.get()
                    .load(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.imagen);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
