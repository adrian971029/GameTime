package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.adrian971029.gametime.base.BaseFragment;
import com.adrian971029.gametime.R;
import com.adrian971029.gametime.adapter.ScreenSlidePagerAdapter;
import com.adrian971029.gametime.database.dto.Pessoa;
import com.adrian971029.gametime.helper.Constainst;
import com.adrian971029.gametime.helper.ZoomOutPageTransformer;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener{

    public static final int NUM_PAGES = 3;

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;

    private Context context;
    private Resources resources;
    private SharedPreferences preferences;
    private PagerAdapter pagerAdapter;
    private MediaPlayer mediaPlayerChangePage;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();
        preferences = getSharedPreferences(Constainst.GERAL_PREFERENCES, Context.MODE_PRIVATE);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(SelectionActivity.this);

        mediaPlayerChangePage = MediaPlayer.create(getApplicationContext(), R.raw.swipe_pager_sound);
        setupPager();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getParcelable(Constainst.PESSOA) != null) {
                pessoa = bundle.getParcelable(Constainst.PESSOA);
                navigationView.getMenu().getItem(0).setTitle(pessoa.getNickname());
                HashMap<String, String> map = new HashMap<>();
                map.put(Constainst.PESSOA_NOME, pessoa.getNome());
                map.put(Constainst.PESSOA_SOBRENOME, pessoa.getSobrenome());
                map.put(Constainst.PESSOA_NICKNAME, pessoa.getNickname());
                map.put(Constainst.PESSOA_EMAIL, pessoa.getEmail());
                map.put(Constainst.PESSOA_SENHA, pessoa.getSenha());
                salvarMapa(Constainst.PESSOA_INFO, map, preferences);
            }
        } else if (!preferences.getString(Constainst.PESSOA_INFO, "").equals("")) {
            Map<String, String> mapPessoa = carregarMapa(Constainst.PESSOA_INFO, preferences);
            pessoa = new Pessoa();
            pessoa.setNome(mapPessoa.get(Constainst.PESSOA_NOME));
            pessoa.setSobrenome(mapPessoa.get(Constainst.PESSOA_SOBRENOME));
            pessoa.setNickname(mapPessoa.get(Constainst.PESSOA_NICKNAME));
            pessoa.setEmail(mapPessoa.get(Constainst.PESSOA_EMAIL));
            pessoa.setSenha(mapPessoa.get(Constainst.PESSOA_SENHA));
            navigationView.getMenu().getItem(0).setTitle(pessoa.getNickname());
        }

    }

    private void setupPager() {
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), context);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mediaPlayerChangePage.start();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            createDialogSair(this, resources.getString(R.string.lbl_deseja_sair));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_one:
                break;
            case R.id.nav_item_two:
                break;
            case R.id.nav_item_three:
                break;
            case R.id.nav_item_four:
                limparUsuario(Constainst.PESSOA_INFO);
                launchLoginScreen();
                break;
        }
        return false;
    }

    private void salvarMapa(String key, HashMap<String,String> inputMap, SharedPreferences preferences){
        if (preferences != null){
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key).apply();
            editor.putString(key, jsonString);
            editor.apply();
        }
    }

    private  HashMap<String,String> carregarMapa(String key, SharedPreferences preferences){
        HashMap<String,String> outputMap = new HashMap<String,String>();
        try{
            if (preferences != null){
                String jsonString = preferences.getString(key, (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while(keysItr.hasNext()) {
                    String k = keysItr.next();
                    String v = (String) jsonObject.get(k);
                    outputMap.put(k,v);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }

    private void limparUsuario(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key).apply();
    }

    private void launchLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

}
