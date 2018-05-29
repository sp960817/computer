package com.example.lc.computer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import ex.SqlHelper;

/**
 * Created by Administrator on 2018/3/17.
 */

public class FinishFragment extends Fragment{
    TextView o_grade,m_grade,j_grade,g_grade,all_grade,finish1;
    Button finish;
    String id,name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.finish_fragment,container,false);
        intview(view);
        //获取总成绩
        float all_n =((MainActivity)getActivity()).getN_all();
        final float ograge = (float)((MainActivity)getActivity()).getO_grade()/all_n*100;
        final float jgrade = (float)((MainActivity)getActivity()).getJ_grade()/all_n*100;
        final float mgrade = (float)((MainActivity)getActivity()).getM_grade()/all_n*100;
        final float ggrade = (float)((MainActivity)getActivity()).getG_grade()/all_n*100;
        final float allgrade =(float)((MainActivity)getActivity()).getAllgrade()/all_n*100;
        id =((MainActivity)getActivity()).getId();
        name =((MainActivity)getActivity()).getName();
        SimpleDateFormat df =new SimpleDateFormat("MMddHHmmss");
        //显示成绩
        o_grade.setText(String.format("%.2f", ograge).toString());
        j_grade.setText(String.format("%.2f", jgrade).toString());
        m_grade.setText(String.format("%.2f", mgrade).toString());
        g_grade.setText(String.format("%.2f", ggrade).toString());
        all_grade.setText(String.format("%.2f", allgrade).toString());
        Connection connection = SqlHelper.openConnection();
        try{//上传成绩
            Statement statement =connection.createStatement();
            String testid =df.format(new Date());
            int count = statement.executeUpdate("INSERT INTO grade VALUES('"+testid+"','"+id+"','"+name+"'," +
                    "'"+ograge+"','"+mgrade+"','"+jgrade+"','"+ggrade+"','"+allgrade+"')");
            if (count>0){
                Toast.makeText(getContext(),"上传成功",Toast.LENGTH_SHORT).show();
                finish1.setVisibility(View.VISIBLE);
            }else {
                try {
                    Thread.currentThread().sleep(1000);//阻断1秒
                    statement.executeUpdate("INSERT INTO grade VALUES('"+testid+"','"+id+"','"+name+"'," +
                            "'"+ograge+"','"+mgrade+"','"+jgrade+"','"+ggrade+"','"+allgrade+"')");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){

        }
        Log.d("sss", String.valueOf(allgrade));
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).Changefragment(new FirstFragment());
            }
        });
        return view;
    }
    private void intview(View view){
        o_grade=(TextView)view.findViewById(R.id.O_grade);
        m_grade=(TextView)view.findViewById(R.id.M_grade);
        j_grade=(TextView)view.findViewById(R.id.J_grade);
        g_grade=(TextView)view.findViewById(R.id.G_grade);
        all_grade=(TextView)view.findViewById(R.id.ALL_grade);
        finish =(Button)view.findViewById(R.id.finish);
        finish1=(TextView)view.findViewById(R.id.finish1);
    }
}
