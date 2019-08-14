package com.adrian971029.gametime.adapter;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.view.SelectionActivity;
import com.adrian971029.gametime.view.fragment.ItemGameFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] nomesList;
    private String[] descList;

    public ScreenSlidePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        nomesList = context.getResources().getStringArray(R.array.game_nome);
        descList = context.getResources().getStringArray(R.array.game_desc);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ItemGameFragment(ContextCompat.getDrawable(context, R.drawable.icone_bingo),nomesList[position], descList[position]);
            case 1:
                return new ItemGameFragment(ContextCompat.getDrawable(context, R.drawable.icone_jogo_da_velha),nomesList[position], descList[position]);
            case 2:
                return new ItemGameFragment(ContextCompat.getDrawable(context, R.drawable.icone_the_journey),nomesList[position], descList[position]);
            default:
                return new ItemGameFragment(ContextCompat.getDrawable(context, R.drawable.icone_bingo),nomesList[position], descList[position]);
        }
    }

    @Override
    public int getCount() {
        return SelectionActivity.NUM_PAGES;
    }
}