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
import com.andres00099216.parcial2.db.Entidades.PlayerEnt;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andres on 15/6/2018.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private Context context;
    private List<PlayerEnt> list;

    public PlayerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        PlayerEnt playerEntity = list.get(position);

        holder.player_name.setText(playerEntity.getDesc());
        holder.player_bio.setText(playerEntity.getJuego());

        if (playerEntity.getNombre() != null) {
            Picasso.get().load(playerEntity.getNombre()).error(R.drawable.
                    ic_launcher_background).into(holder.player_avatar);
        } else {
            Picasso.get().load(R.drawable.ic_launcher_background).
                    error(R.drawable.ic_launcher_background).into(holder.player_avatar);
        }

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

        ImageView player_avatar;
        TextView player_name, player_bio;

        public ViewHolder(View itemView) {
            super(itemView);

            player_avatar = itemView.findViewById(R.id.imageView_playerCardViewImage);
            player_name = itemView.findViewById(R.id.textView_playerCardViewName);
            player_bio = itemView.findViewById(R.id.textView_playerCardViewBio);
        }

    }

    public void setList(List<PlayerEnt> playerEntitiesList) {
        this.list = playerEntitiesList;
    }
}
