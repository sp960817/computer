package com.example.lc.computer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ex.SqlHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView username,userpw;
    Button login,clear,register;
    Connection connection;
    ResultSet resultSet;
    Statement statement;
    private static final int ok=1,nopw=2,noname=3;
    private Handler handler =new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case ok:
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    break;
                case nopw:
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    break;
                case noname:
                    Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        intview();
    }
    private void intview(){
        username =(TextView)findViewById(R.id.username);
        userpw =(TextView)findViewById(R.id.userpw);
        login = (Button)findViewById(R.id.login);
        clear =(Button)findViewById(R.id.clear);
        register = (Button)findViewById(R.id.register);
        login.setOnClickListener(this);
        clear.setOnClickListener(this);
        register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear:
                username.setText("");
                userpw.setText("");
                break;
            case R.id.login:
                final String name =username.getText().toString().trim();
                final String pw = userpw.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connection = SqlHelper.openConnection();
                        try{
                            statement =connection.createStatement();
                            resultSet = statement.executeQuery("SELECT * FROM student WHERE studentID = '" + name + "'");
                            if(resultSet.next()){
                                String sqlpw = resultSet.getString("password");
                                String name1 = resultSet.getString("name");
                                if(pw.equals(sqlpw)){
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("name_e",name1);
                                    intent.putExtra("id_e",name);
                                    startActivity(intent);
                                }else {
                                    Message message =new Message();
                                    message.what =nopw;
                                    handler.sendMessage(message);
                                }
                            }else {
                                Message message =new Message();
                                message.what =noname;
                                handler.sendMessage(message);
                            }
                            resultSet.close();
                            connection.close();
                        }catch (SQLException e){
                        }
                    }
                }).start();
                break;
            case R.id.register:
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                    break;

        }
    }
}
