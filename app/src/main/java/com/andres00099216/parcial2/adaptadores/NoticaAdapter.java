package com.andres00099216.parcial2.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andres00099216.parcial2.R;
import com.andres00099216.parcial2.db.Entidades.NoticiaEnt;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andres on 14/6/2018.
 */

public abstract class NoticaAdapter extends RecyclerView.Adapter<NoticaAdapter.ViewHolder> {

    private Context context;
    private List<NoticiaEnt> list;

    public NoticaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticiaEnt news = list.get(position);

        holder.title = news.getNotTittle();
        holder.image = news.getNotCoverImage();
        holder.description = news.getNotDescription();
        holder.body = news.getNotBody();

        holder.titulo.setText(holder.title);

        if (news.getNotCoverImage() != null) {
            Picasso.get().load(news.getNotCoverImage()).error(R.drawable.ic_launcher_background).into(holder.cover);
        } else {
            Picasso.get().load(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.cover);
        }
        holder.desc.setText(holder.description);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo, desc;
        private ImageView cover;


        private String title, image, description, body;

        public ViewHolder(View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.imagen_noticia);
            titulo = itemView.findViewById(R.id.titulo_noticia);
            desc = itemView.findViewById(R.id.descripcion_noticia);

        }
    }

    public void setList(List<NoticiaEnt> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
