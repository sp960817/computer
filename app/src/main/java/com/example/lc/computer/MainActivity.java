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
    int o_grade,m_grade,j_grade,g_grade;
    int o_n,j_n,g_n,m_n,n_all;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化分数
        o_grade=0;
        m_grade=0;
        j_grade=0;
        g_grade=0;
        setContentView(R.layout.activity_main);
        Intent intent =getIntent();
        id =intent.getStringExtra("id_e");
        Log.d("sss", id);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        name =intent.getStringExtra("name_e");
        replaceFragment(new FirstFragment());//更换初始化页面
    }
    private void replaceFragment(Fragment fragment){//碎片更换
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_view,fragment);
        transaction.commit();
    }
    public void reFragment(Fragment fragment){//碎片更换
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_view,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void Changefragment(Fragment fragment){//碎片更换
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_view,fragment);
        transaction.commit();
    }
    //主Activity写入public函数 对分数，id进行更改，获取
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

    public void setM_grade(int m_grade) {
        this.m_grade = m_grade;
    }

    public int getM_grade() {
        return m_grade;
    }

    public int getJ_grade() {
        return j_grade;
    }

    public void setJ_grade(int j_grade) {
        this.j_grade = j_grade;
    }

    @Override
    public void onClick(View v) {

    }
    public int getG_grade() {
        return g_grade;
    }
    public void setG_grade(int g_grade) {
        this.g_grade = g_grade;
    }
    public int getAllgrade(){
        int allgrage =g_grade+j_grade+m_grade+o_grade;
        return allgrage;
    }

    public void setG_n(int g_n) {
        this.g_n = g_n;
    }

    public void setJ_n(int j_n) {
        this.j_n = j_n;
    }

    public void setM_n(int m_n) {
        this.m_n = m_n;
    }

    public void setO_n(int o_n) {
        this.o_n = o_n;
    }

    public int getO_n() {
        return o_n;
    }

    public int getM_n() {
        return m_n;
    }

    public int getJ_n() {
        return j_n;
    }

    public int getG_n() {
        return g_n;
    }

    public int getN_all() {
        int n_all =g_n+j_n+m_n+o_n;
        return n_all;
    }
}
