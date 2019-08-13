package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian971029.gametime.BaseActivity;
import com.adrian971029.gametime.R;
import com.adrian971029.gametime.adapter.GameAdapter;
import com.adrian971029.gametime.model.Game;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends BaseActivity {

    @BindView(R.id.rc_gameList)
    RecyclerView rcGameList;

    private Context context;
    private Resources resources;
    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();

        setupRecycler();
        alimentarGameList();

    }

    private void alimentarGameList() {
        ArrayList<Game> gameList = new ArrayList<>();
        gameList.add(new Game(resources.getString(R.string.lbl_bingo), resources.getString(R.string.lbl_desc_bingo)));
        gameList.add(new Game(resources.getString(R.string.lbl_jogoVelha), resources.getString(R.string.lbl_desc_jogoVelha)));
        gameList.add(new Game(resources.getString(R.string.lbl_theJorney), resources.getString(R.string.lbl_desc_theJorney)));
        gameAdapter = new GameAdapter(context, gameList);
        rcGameList.setAdapter(gameAdapter);
        gameAdapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcGameList.setLayoutManager(layoutManager);
        rcGameList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        createDialogSair(this, resources.getString(R.string.lbl_deseja_sair));
    }

}
