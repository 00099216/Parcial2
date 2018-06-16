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
    private List<PlayerEnt> playerList;

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
        PlayerEnt player= playerList.get(position);
        holder.textView.setText(player.getNombre());
        if (player.getAvatar() != null)
            Picasso.get().load(player.getAvatar()).error(R.drawable.ic_launcher_background).into(holder.imageView);
        else
            Picasso.get().load(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if (playerList == null){
            return 0;
        }else {
            return playerList.size();
        }
    }
    public void setPlayerList(List<PlayerEnt> playerEntitiesList){
        this.playerList = playerEntitiesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.player_foto);
            textView = itemView.findViewById(R.id.player_description);
        }
    }
}
