package com.example.kunalparte.styfitask.Utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kunalparte.styfitask.R;

public class BaseActivity extends AppCompatActivity{

    SharedPreferences sharedPreferences;

    String currentTheme ="", selectedTeme = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_App_Red);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume(){
        super.onResume();


    }
}
