package com.example.lc.computer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ex.Fruit;
import ex.FruitAdapter;
import ex.SqlHelper;

/**
 * Created by sp on 2018/4/2.
 */

public class SeeGradeFragement extends Fragment {
    private List<Fruit> fruits =new ArrayList<>();
    FruitAdapter adapter;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.seegrade_fragment,container,false);
        getFruit();
        ListView listView = (ListView)view.findViewById(R.id.see_grade_list);
        adapter=new FruitAdapter(view.getContext(),R.layout.see_grade_list,fruits);
        listView.setAdapter(adapter);//listView绑定适配器
        adapter.notifyDataSetChanged();
        return view;
    }
    private void getFruit(){//从数据库中获取成绩
        try{
            connection = SqlHelper.openConnection();
            statement =connection.createStatement();
            String idid = ((MainActivity)getActivity()).getId();
            resultSet =statement.executeQuery("SELECT * FROM grade WHERE studentID ='"+idid+"'");
            while(resultSet.next()){
                Log.d("sss", resultSet.getString("testID"));
                fruits.add(new Fruit(resultSet.getString("testID"),resultSet.getString("only_choose"),resultSet.getString("many_choose"),resultSet.getString("gapfilling"),resultSet.getString("judge"),resultSet.getString("grade")));
            }
            resultSet.close();
            connection.close();
        }catch (SQLException e){
            Log.d("sss", "err 1");
        }
    }
}
