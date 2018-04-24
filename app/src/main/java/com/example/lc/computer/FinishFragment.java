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
        final int ograge = ((MainActivity)getActivity()).getO_grade();
        final int jgrade = ((MainActivity)getActivity()).getJ_grade();
        final int mgrade = ((MainActivity)getActivity()).getM_grade();
        final int ggrade = ((MainActivity)getActivity()).getG_grade();
        final int allgrade =((MainActivity)getActivity()).getAllgrade();
        id =((MainActivity)getActivity()).getId();
        name =((MainActivity)getActivity()).getName();
        SimpleDateFormat df =new SimpleDateFormat("MMddHH");
        final String testid =df.format(new Date())+id;
        //显示成绩
        o_grade.setText(Integer.toString(ograge));
        j_grade.setText(Integer.toString(jgrade));
        m_grade.setText(Integer.toString(mgrade));
        g_grade.setText(Integer.toString(ggrade));
        all_grade.setText(Integer.toString(allgrade));
        Connection connection = SqlHelper.openConnection();
        try{//上传成绩
            Statement statement =connection.createStatement();
            int count = statement.executeUpdate("INSERT INTO grade VALUES('"+testid+"','"+id+"','"+name+"'," +
                    "'"+ograge+"','"+mgrade+"','"+jgrade+"','"+ggrade+"','"+allgrade+"')");
            if (count>0){
                Toast.makeText(getContext(),"上传成功",Toast.LENGTH_SHORT).show();
                finish1.setVisibility(View.VISIBLE);
            }
        }catch (SQLException e){

        }
        Log.d("sss", Integer.toString(allgrade));
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
