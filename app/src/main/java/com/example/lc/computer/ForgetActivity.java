package com.example.lc.computer;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.MessageDialog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ex.SqlHelper;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener{
    EditText forget_id,forget_name;
    Button forget_password,main_return;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        intview();
    }
    private void intview(){//初始化页面
        forget_id =(EditText)findViewById(R.id.forget_id);
        forget_name =(EditText)findViewById(R.id.forget_name);
        forget_password =(Button)findViewById(R.id.getpassowrd);
        main_return =(Button)findViewById(R.id.main_return);
        forget_password.setOnClickListener(this);
        main_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){//获取密码事件
            case R.id.getpassowrd:
                String name=null,password = null;
                String id =forget_id.getText().toString();
                String Fname =forget_name.getText().toString().trim();
                connection = SqlHelper.openConnection();//连接数据库
                try{
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM student WHERE studentID = "+id+"");
                    if (resultSet.next()){
                        name = resultSet.getString("name");
                        password = resultSet.getString("password");
                        if (Fname.equals(name)){
                            //Toast.makeText(this,"您的密码是"+ password +"",Toast.LENGTH_LONG).show();
                            DialogSettings.type =DialogSettings.TYPE_MATERIAL;
                            MessageDialog.show(this,"您的密码是",""+password+"");
                        }else {
                            Toast.makeText(this,"信息有误",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(this,"不存在的学号",Toast.LENGTH_LONG).show();
                    }

                }catch (SQLException e){

                }
                break;
            case R.id.main_return:
                finish();
                break;
                default:
                    break;
        }
    }
}
