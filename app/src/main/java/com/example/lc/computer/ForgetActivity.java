package com.example.lc.computer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }
    private void intview(){
        forget_id =(EditText)findViewById(R.id.forget_id);
        forget_name =(EditText)findViewById(R.id.forget_name);
        forget_password =(Button)findViewById(R.id.forget_pwd);
        main_return =(Button)findViewById(R.id.main_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_pwd:
                connection = SqlHelper.openConnection();
                try{
                    statement = connection.createStatement();

                }catch (SQLException e){

                }
        }
    }
}
