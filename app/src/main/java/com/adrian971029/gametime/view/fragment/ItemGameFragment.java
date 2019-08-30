package com.adrian971029.gametime.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.view.JogoVelhaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemGameFragment extends Fragment {

    public static final String EXTRA_DRAWABLE = "drawable";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESC = "desc";

    @BindView(R.id.img_game)
    ImageView imgGame;
    @BindView(R.id.tv_gameTitle)
    TextView tvGameTitle;
    @BindView(R.id.tv_gameDesc)
    TextView tvGameDesc;
    @BindView(R.id.bt_jogar)
    Button btnJogar;

    private Context context;
    private Resources resources;
    private Drawable drawable;
    private String title;
    private String desc;

    public static final ItemGameFragment newInstance(int drawableResource, String title, String desc) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_DRAWABLE, drawableResource);
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_DESC, desc);
        ItemGameFragment fragment = new ItemGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_game, container, false);

        ButterKnife.bind(this, rootView);

        context = getActivity();
        resources = context.getResources();

        drawable = ContextCompat.getDrawable(context,getArguments().getInt(EXTRA_DRAWABLE));
        title = getArguments().getString(EXTRA_TITLE);
        desc = getArguments().getString(EXTRA_DESC);

        if (title.equals(resources.getString(R.string.lbl_theJorney))) {
            if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imgGame.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                imgGame.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        } else {
            imgGame.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }

        imgGame.setImageDrawable(drawable);
        tvGameTitle.setText(title);
        tvGameDesc.setText(desc);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.bt_jogar})
    void onActionBtnJogar(){
        if (title.equals(resources.getString(R.string.lbl_theJorney))) {
            Toast.makeText(context, "The Jorney", Toast.LENGTH_SHORT).show();
        } else if (title.equals(resources.getString(R.string.lbl_jogoVelha))){
            Intent abrirActivityJogoVelha = new Intent(context, JogoVelhaActivity.class);
            startActivity(abrirActivityJogoVelha);
        } else if (title.equals(resources.getString(R.string.lbl_bingo))){
            Toast.makeText(context, "Bingo", Toast.LENGTH_SHORT).show();
        }
    }

}
