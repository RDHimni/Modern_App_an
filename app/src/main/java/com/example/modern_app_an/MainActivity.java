package com.example.modern_app_an;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.SlidingRootNavLayout;

import java.util.Arrays;

import kotlin.jvm.internal.PropertyReference0Impl;

public class MainActivity extends AppCompatActivity implements  DrawerAdapter.OnItemSelectedListener{

    public static final int POS_CLOSE = 0;
    public static final int POS_DASHBOARD = 1;
    public static final int POS_MY_PROFILE = 2;
    public static final int POS_NEARBY_RES = 3;
    public static final int POS_SETTINGS = 4;
    public static final int POS_ABOUT_US = 5;
    public static final int POS_LOGOUT= 7;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();



        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();



        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                creatItemFor(POS_CLOSE),
                creatItemFor(POS_DASHBOARD).setChecked(true),
                creatItemFor(POS_MY_PROFILE),
                creatItemFor(POS_NEARBY_RES),
                creatItemFor(POS_SETTINGS),
                creatItemFor(POS_ABOUT_US),
                new SpaceItem(100),
                creatItemFor(POS_LOGOUT)

                ));


        adapter.setListener(this);



        RecyclerView list = findViewById(R.id.drawer_list);

        list.setNestedScrollingEnabled(false);

        list.setLayoutManager(new LinearLayoutManager(this));

        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);

    }

    private DrawerItem creatItemFor(int position){
        return new SimpleItem(screenIcons[position],screenTitles[position])
                .withIconTint(color(R.color.Pink))
                .withTextTint(color(R.color.Black))
                .withSelectedIconTint(color(R.color.Pink))
                .withSelectedTextTint(color(R.color.Pink));

    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i=0; i<ta.length(); i++) {
            int id = ta.getResourceId(i,0);
            if (id !=0){
                icons[i] = ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
    }
    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitle);
    }
    @Override
    public void onBackPressed() {


        int count = getSupportFragmentManager().getBackStackEntryCount();

        Toast.makeText(this, ""+count, Toast.LENGTH_SHORT).show();


        if (count == 1) {
            //super.onBackPressed();
            finish();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }



    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_DASHBOARD){

            Fragment hhhhh =  new DashboardFragment();
            transaction.replace(R.id.container,hhhhh);


        }
        if (position == POS_MY_PROFILE){
            Fragment hhhhh =  CenteredTextFragment.createFor(screenTitles[position]);
            transaction.replace(R.id.container,hhhhh);
        }
        if (position == POS_NEARBY_RES){
            Fragment hhhhh =  CenteredTextFragment.createFor(screenTitles[position]);
            transaction.replace(R.id.container,hhhhh);
        }

        if (position == POS_SETTINGS){
            Fragment hhhhh =  CenteredTextFragment.createFor(screenTitles[position]);
            transaction.replace(R.id.container,hhhhh);
        }

        if (position == POS_ABOUT_US){
            Fragment hhhhh =  CenteredTextFragment.createFor(screenTitles[position]);
            transaction.replace(R.id.container,hhhhh);
        }

        if (position == POS_LOGOUT){
            finish();
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }




}