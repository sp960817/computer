package com.example.lc.computer;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ex.OcDatabase;
import ex.SqlHelper;

/**
 * Created by sp on 2018/3/12.
 */

public class FirstFragment extends Fragment {
    private OcDatabase dbHelper;
    int[] nu;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment,container,false);
        TextView welcome =(TextView)view.findViewById(R.id.welcometext);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String name =((MainActivity)getActivity()).getName();
        welcome.setText("欢迎你，"+name);

        nu = com.example.lc.computer.Random.randomCommon(1,11,5);
        Button ok=(Button)view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
                DataSupport.deleteAll(OC.class);
                DataSupport.deleteAll(MC.class);
                getOC();
                getMC();
                getJUDGE();
                getGapfilling();
                ((MainActivity)getActivity()).Changefragment(new OnlyChooseFragement());
            }
        });
        return view;
    }
    //获取单选题
    private void getOC(){
        String a_subject=null,a_A = null,a_B=null,a_C=null,a_D=null,a_answer=null;
        connection = SqlHelper.openConnection();
        try {
            statement =connection.createStatement();
            for(int i=0;i<5;i++){
                Log.d("sss",Integer.toString(nu[i]));
            }
            OC oc =new OC();
            for(int j=0;j<5;j++){
                resultSet =statement.executeQuery("SELECT * FROM only_choose WHERE id = '"+nu[j]+"'");
                if (resultSet.next()){
                    a_subject =resultSet.getString("subject");
                    a_A =resultSet.getString("A");
                    a_B =resultSet.getString("B");
                    a_C =resultSet.getString("C");
                    a_D =resultSet.getString("D");
                    a_answer=resultSet.getString("answer");
                }
                oc.setIdid(j+1);
                oc.setSubject(a_subject);
                oc.setA(a_A);
                oc.setB(a_B);
                oc.setC(a_C);
                oc.setD(a_D);
                oc.setAnswer(a_answer);
                oc.save();
                oc.clearSavedState();
                Log.d("sss", "增加题"+nu[j]);

            }

        }catch (SQLException e){

        }
    }
    //获取判断题
    private void getJUDGE(){
        String a_subject =null,a_answer=null;
        connection =SqlHelper.openConnection();
        try {
            JUDGE judge =new JUDGE();
            statement =connection.createStatement();
            for(int j = 0; j<5;j++){
                resultSet =statement.executeQuery("SELECT * FROM judge WHERE id = '"+nu[j]+"'");
                if(resultSet.next()){
                    a_subject=resultSet.getString("subject");
                    a_answer =resultSet.getString("answer");
                }
                judge.setIdid(j+1);
                judge.setSubject(a_subject);
                judge.setAnswer(a_answer);
                judge.save();
                judge.clearSavedState();
            }
        }catch (SQLException e){

        }
    }
    //获取多选题
    private void getMC(){
        String a_subject=null,a_A = null,a_B=null,a_C=null,a_D=null,a_answer=null;
        connection = SqlHelper.openConnection();
        try {
            MC mc =new MC();
            statement =connection.createStatement();
            for(int j=0;j<5;j++){
                resultSet =statement.executeQuery("SELECT * FROM many_choose WHERE id = '"+nu[j]+"'");
                if (resultSet.next()){
                    a_subject =resultSet.getString("subject");
                    a_A =resultSet.getString("A");
                    a_B =resultSet.getString("B");
                    a_C =resultSet.getString("C");
                    a_D =resultSet.getString("D");
                    a_answer=resultSet.getString("answer");
                }
                mc.setIdid(j+1);
                mc.setSubject(a_subject);
                mc.setA(a_A);
                mc.setB(a_B);
                mc.setC(a_C);
                mc.setD(a_D);
                mc.setAnswer(a_answer);
                mc.save();
                mc.clearSavedState();
            }
        }catch (SQLException e){

        }
    }
    private void getGapfilling(){
        String a_subject =null,a_answer=null;
        connection =SqlHelper.openConnection();
        try {
            GAPFILLING gapfilling =new GAPFILLING();
            statement =connection.createStatement();
            for(int j = 0; j<5;j++){
                resultSet =statement.executeQuery("SELECT * FROM gapfilling WHERE id = '"+nu[j]+"'");
                if(resultSet.next()){
                    a_subject=resultSet.getString("subject");
                    a_answer =resultSet.getString("answer");
                }
                gapfilling.setIdid(j+1);
                gapfilling.setSubject(a_subject);
                gapfilling.setAnswer(a_answer);
                gapfilling.save();
                gapfilling.clearSavedState();
            }
        }catch (SQLException e){

        }
    }
}
