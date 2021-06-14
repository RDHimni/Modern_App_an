package com.example.modern_app_an;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter  {

    private Context context;
    private int totalTabs;


    public LoginAdapter(Context context,int totalTabs,@NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                login_tab_fragment login_tab_fragment = new login_tab_fragment();
                return login_tab_fragment;
            case 1:
                signup_tab_fragment signup_tab_fragment = new signup_tab_fragment();
                return signup_tab_fragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
