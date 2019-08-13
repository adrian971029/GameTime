package com.adrian971029.gametime.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.model.Game;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private ArrayList<Game> gameArrayList;
    private Context context;
    private Resources resources;

    public GameAdapter(Context context, ArrayList<Game> gameArrayList) {
        this.context = context;
        this.gameArrayList = gameArrayList;
        resources = context.getResources();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_game, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final Game game = gameArrayList.get(i);
        if (game.getTitle().equals(resources.getString(R.string.lbl_theJorney))) {
            holder.imgGame.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.imgGame.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icone_the_journey));
        } else if (game.getTitle().equals(resources.getString(R.string.lbl_bingo))) {
            holder.imgGame.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.imgGame.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icone_bingo));
        } else if (game.getTitle().equals(resources.getString(R.string.lbl_jogoVelha))) {
            holder.imgGame.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.imgGame.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icone_jogo_da_velha));
        }
        holder.tvGameTitle.setText(game.getTitle());
        holder.tvGameDesc.setText(game.getDesc());
    }

    @Override
    public int getItemCount() {
        if (gameArrayList != null) {
            return gameArrayList.size();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_game)
        ImageView imgGame;
        @BindView(R.id.tv_gameTitle)
        TextView tvGameTitle;
        @BindView(R.id.tv_gameDesc)
        TextView tvGameDesc;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }


}
