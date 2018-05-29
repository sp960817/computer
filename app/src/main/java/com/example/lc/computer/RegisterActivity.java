package com.example.lc.computer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ex.SqlHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button register,r_return;
    TextView id,name,r_class,password;
    public static final int ok =1,no=2;
    private String TAG="sss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        intview();
    }
    //获取控件
    private void intview(){
        register = (Button)findViewById(R.id.register);
        r_return =(Button)findViewById(R.id.r_retrun);
        id =(TextView)findViewById(R.id.userid);
        name =(TextView)findViewById(R.id.username);
        r_class =(TextView)findViewById(R.id.userclass);
        password =(TextView)findViewById(R.id.userpw);
        register.setOnClickListener(this);
        r_return.setOnClickListener(this);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r_retrun://返回事件
                finish();
                break;
            case R.id.register://注册事件
                final String u_id = id.getText().toString().trim();
                final String u_name = name.getText().toString().trim();
                final String u_class = r_class.getText().toString().trim();
                final String u_password =password.getText().toString().trim();
                if(u_id.equals("")) {
                    Toast.makeText(RegisterActivity.this,"学号不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(u_name.equals("")){
                    Toast.makeText(RegisterActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(u_class.equals("")){
                    Toast.makeText(RegisterActivity.this,"班级不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(u_password.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                Connection connection= SqlHelper.openConnection();
                try{//数据写入数据库
                    Statement statement=connection.createStatement();
                    Log.d(TAG, u_id);
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM student WHERE studentID ='"+u_id+"'");
                    if(resultSet.next()){
                        Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d(TAG, "2");
                        int count = statement.executeUpdate("INSERT INTO student VALUES('"+u_id+"','"+u_name+"','"+u_class+"','"+u_password+"')");
                        if (count>0){
                            Toast.makeText(RegisterActivity.this,"注册成功,请返回",Toast.LENGTH_SHORT).show();
                        }
                    }
                    connection.close();
                }catch (SQLException e){
                    e.getNextException();
                }
                break;
            default:
                    break;
        }
    }
}
