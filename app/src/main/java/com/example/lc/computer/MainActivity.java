package com.example.lc.computer;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String name;
    String id ;
    int o_grade,m_grade,j_grade,g_drade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        o_grade=0;
        m_grade=0;
        j_grade=0;
        g_drade=0;

        setContentView(R.layout.activity_main);
        Intent intent =getIntent();
        id =intent.getStringExtra("id_e");
        Log.d("sss", id);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        name =intent.getStringExtra("name_e");
        replaceFragment(new FirstFragment());
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_view,fragment);
        transaction.commit();
    }
    public void Changefragment(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_view,fragment);
        transaction.commit();
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public void setO_grade(int o_grade){
        this.o_grade=o_grade;
    }
    public int getO_grade(){
        return o_grade;
    }
    @Override
    public void onClick(View v) {

    }
}
