package com.example.modern_app_an;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class login_tab_fragment extends Fragment {

    EditText email, pass;
    TextView forgetpassword;
    Button Login;

    final float v =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetpassword = root.findViewById(R.id.forgetpass);
        Login = root.findViewById(R.id.button_login);


        email.setTranslationY(300);
        pass.setTranslationY(300);
        forgetpassword.setTranslationY(300);
        Login.setTranslationY(300);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetpassword.setAlpha(v);
        Login.setAlpha(v);


        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        Login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }
}