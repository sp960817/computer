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
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import ex.SqlHelper;

/**
 * Created by sp on 2018/3/12.
 */

public class FirstFragment extends Fragment {
    int[] nu;
    int o_max,m_max,g_max,j_max;
    int o_n,j_n,g_n,m_n;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_fragment, container, false);
        TextView welcome = (TextView) view.findViewById(R.id.welcometext);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//允许主线程访问网络
        String name = ((MainActivity) getActivity()).getName();
        welcome.setText("欢迎你，" + name);
        final Button ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//初始化试题 随机获取试题
                int YZ =YZ(view);
                if(YZ>0){
                    Toast.makeText(view.getContext(),"今天已经做过试题了",Toast.LENGTH_LONG).show();
                }else {
                    LitePal.getDatabase();
                    DataSupport.deleteAll(OC.class);
                    DataSupport.deleteAll(MC.class);
                    DataSupport.deleteAll(GAPFILLING.class);
                    DataSupport.deleteAll(JUDGE.class);
                    getOC();
                    getMC();
                    getJUDGE();
                    getGapfilling();
                    ((MainActivity) getActivity()).reFragment(new OnlyChooseFragement());
                }

            }
        });
        Button see_grade = (Button) view.findViewById(R.id.see_grade);
        see_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).reFragment(new SeeGradeFragement());
            }
        });
        return view;
    }
    private int YZ(View view){
        SimpleDateFormat df =new SimpleDateFormat("MMdd");
        String time =df.format(new Date());
        String id =((MainActivity)getActivity()).getId();
        String mysql ="SELECT * FROM grade WHERE testid LIKE '"+time+"%' AND studentID = '"+id+"'";
        connection =SqlHelper.openConnection();
        try{
            statement =connection.createStatement();
            resultSet  =statement.executeQuery(mysql);
            if(resultSet.next()){

                return 1;
            }else {
                return 0;
            }
        }catch (SQLException e){
            return 0;
        }
    }
    //获取单选题
    private void getOC() {
        o_max = GetMax("only_choose");
        o_n =GetN("only_choose");
        ((MainActivity)getActivity()).setO_n(o_n);
        nu =Random.randomCommon(1,o_max,o_n);
        String a_subject = null, a_A = null, a_B = null, a_C = null, a_D = null, a_answer = null;
        connection = SqlHelper.openConnection();
        try {
            statement = connection.createStatement();
            OC oc = new OC();
            for (int j = 0; j < o_n; j++) {
                resultSet = statement.executeQuery("SELECT * FROM only_choose WHERE id = '" + nu[j] + "'");
                if (resultSet.next()) {
                    a_subject = resultSet.getString("subject");
                    a_A = resultSet.getString("A");
                    a_B = resultSet.getString("B");
                    a_C = resultSet.getString("C");
                    a_D = resultSet.getString("D");
                    a_answer = resultSet.getString("answer");
                }
                oc.setIdid(j + 1);
                oc.setSubject(a_subject);
                oc.setA(a_A);
                oc.setB(a_B);
                oc.setC(a_C);
                oc.setD(a_D);
                oc.setAnswer(a_answer);
                oc.save();
                oc.clearSavedState();
                Log.d("sss", "增加题" + nu[j]);

            }

        } catch (SQLException e) {

        }
    }

    //获取判断题
    private void getJUDGE() {
        j_max= GetMax("judge");
        j_n =GetN("judge");

        ((MainActivity)getActivity()).setJ_n(j_n);
        nu =Random.randomCommon(1,j_max,j_n);
        String a_subject = null, a_answer = null;
        connection = SqlHelper.openConnection();
        try {
            JUDGE judge = new JUDGE();
            statement = connection.createStatement();
            for (int j = 0; j < j_n; j++) {
                resultSet = statement.executeQuery("SELECT * FROM judge WHERE id = '" + nu[j] + "'");
                if (resultSet.next()) {
                    a_subject = resultSet.getString("subject");
                    a_answer = resultSet.getString("answer");
                }
                judge.setIdid(j + 1);
                judge.setSubject(a_subject);
                judge.setAnswer(a_answer);
                judge.save();
                judge.clearSavedState();
            }
        } catch (SQLException e) {

        }
    }

    //获取多选题
    private void getMC() {
        m_max = GetMax("many_choose");
        m_n =GetN("many_choose");
        nu =Random.randomCommon(1,m_max,m_n);
        ((MainActivity)getActivity()).setM_n(m_n);
        String a_subject = null, a_A = null, a_B = null, a_C = null, a_D = null, a_answer = null;
        connection = SqlHelper.openConnection();
        try {
            MC mc = new MC();
            statement = connection.createStatement();
            for (int j = 0; j < m_n; j++) {
                resultSet = statement.executeQuery("SELECT * FROM many_choose WHERE id = '" + nu[j] + "'");
                if (resultSet.next()) {
                    a_subject = resultSet.getString("subject");
                    a_A = resultSet.getString("A");
                    a_B = resultSet.getString("B");
                    a_C = resultSet.getString("C");
                    a_D = resultSet.getString("D");
                    a_answer = resultSet.getString("answer");
                }
                mc.setIdid(j + 1);
                mc.setSubject(a_subject);
                mc.setA(a_A);
                mc.setB(a_B);
                mc.setC(a_C);
                mc.setD(a_D);
                mc.setAnswer(a_answer);
                mc.save();
                mc.clearSavedState();
            }
        } catch (SQLException e) {

        }
    }

    //获取填空
    private void getGapfilling() {
        g_max = GetMax("gapfilling");
        g_n =GetN("gapfilling");
        ((MainActivity)getActivity()).setG_n(g_n);
        nu =Random.randomCommon(1,g_max,g_n);
        String a_subject = null, a_answer = null;
        connection = SqlHelper.openConnection();
        try {
            GAPFILLING gapfilling = new GAPFILLING();
            statement = connection.createStatement();
            for (int j = 0; j < g_n; j++) {
                resultSet = statement.executeQuery("SELECT * FROM gapfilling WHERE id = '" + nu[j] + "'");
                if (resultSet.next()) {
                    a_subject = resultSet.getString("subject");
                    a_answer = resultSet.getString("answer");
                }
                gapfilling.setIdid(j + 1);
                gapfilling.setSubject(a_subject);
                gapfilling.setAnswer(a_answer);
                gapfilling.save();
                gapfilling.clearSavedState();
            }
        } catch (SQLException e) {

        }
    }

    private int GetN(String xx) {
        connection = SqlHelper.openConnection();
        int  n = 0;
        try {
            statement = connection.createStatement();
            String mysql = "SELECT " + xx + " FROM test_number";
            resultSet = statement.executeQuery(mysql);
            if (resultSet.next()) {
                n = resultSet.getInt("" + xx + "");
                Log.d("sss",Integer.toString(n));
            }
        } catch (SQLException e) {
        }
        return n;
    }
    private int GetMax(String xx){
        connection = SqlHelper.openConnection();
        int max = 5;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM "+xx+" WHERE id = (SELECT MAX(id) FROM "+xx+")");
            if (resultSet.next()) {
                max = resultSet.getInt("id");
            }
        } catch (SQLException e) {
        }
        Log.d("sss", Integer.toString(max));
        return max;
    }


}
