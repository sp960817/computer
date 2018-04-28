package com.example.lc.computer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import ex.OcDatabase;

/**
 * Created by sp on 2018/3/13.
 */
//多选题碎片 Fragment
public class ManyChooseFragment extends Fragment {
    TextView subject;
    CheckBox A,B,C,D;
    Button submit;
    int i,grade;
    String answer,seleanswer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.manychoose_fragment,container,false);
        getview(view);
        i=2;
        grade=0;
        final int max =((MainActivity)getActivity()).getM_n();
        List<MC> mcs = DataSupport.where("idid = ?","1").find(MC.class);
        for(MC mc:mcs){//初始化试题
            subject.setText("1."+mc.getSubject());
            A.setText(mc.getA());
            B.setText(mc.getB());
            C.setText(mc.getC());
            D.setText(mc.getD());
            answer =mc.getAnswer();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//多选逻辑
                    if(A.isChecked()){
                        if (B.isChecked()){
                            if (C.isChecked()){
                                if (D.isChecked()){
                                    seleanswer="ABCD";
                                }else {
                                    seleanswer="ABC";
                                }
                            }else {
                                if (D.isChecked()){
                                    seleanswer="ABD";
                                }else {
                                    seleanswer="AB";
                                }
                            }
                        }else {
                            if (C.isChecked()) {
                                if (D.isChecked()) {
                                    seleanswer = "ACD";
                                } else {
                                    seleanswer = "AC";
                                }
                            } else {
                                if (D.isChecked()) {
                                    seleanswer = "AD";
                                } else {
                                    seleanswer = "A";
                                }
                            }
                        }
                    }else {
                        if (B.isChecked()) {
                            if (C.isChecked()) {
                                if (D.isChecked()) {
                                    seleanswer = "BCD";
                                } else {
                                    seleanswer = "BC";
                                }
                            } else {
                                if (D.isChecked()) {
                                    seleanswer = "BD";
                                } else {
                                    seleanswer = "B";
                                }
                            }
                        } else {
                            if (C.isChecked()) {
                                if (D.isChecked()) {
                                    seleanswer = "CD";
                                } else {
                                    seleanswer = "C";
                                }
                            } else {
                                if (D.isChecked()) {
                                    seleanswer = "D";
                                } else {
                                    seleanswer = "";
                                }
                            }
                        }
                    }
                if(i<=max){//前四题 回答完进入下一题
                    if(seleanswer.equals("")){
                        Toast.makeText(getContext(), "请填写", Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(seleanswer)) {
                            Toast.makeText(getContext(), "答对加分", Toast.LENGTH_SHORT).show();
                            grade=grade+1;
                            A.setChecked(false);
                            B.setChecked(false);
                            C.setChecked(false);
                            D.setChecked(false);
                        } else {
                            Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                            A.setChecked(false);
                            B.setChecked(false);
                            C.setChecked(false);
                            D.setChecked(false);
                        }
                        List<MC> mcs = DataSupport.where("idid = ?",""+i+"").find(MC.class);
                        for(MC mc:mcs){
                            subject.setText(i+"."+mc.getSubject());
                            A.setText(mc.getA());
                            B.setText(mc.getB());
                            C.setText(mc.getC());
                            D.setText(mc.getD());
                            answer =mc.getAnswer();
                        }
                        seleanswer="";
                        i++;
                    }
                }else {//最后一题回答完进入判断Fragment
                    if(seleanswer.equals("")){
                        Toast.makeText(getContext(), "请填写", Toast.LENGTH_SHORT).show();
                    }else {
                        if (answer.equals(seleanswer)) {
                            Toast.makeText(getContext(), "答对加分", Toast.LENGTH_SHORT).show();
                            grade=grade+1;
                        } else {
                            Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                        }
                        ((MainActivity) getActivity()).setM_grade(grade);
                        ((MainActivity)getActivity()).Changefragment(new JudgeFragment());
                        Log.d("sss", Integer.toString(((MainActivity) getActivity()).getM_grade()));
                    }

                }

            }
        });
        return view;
    }
    private void getview(View view){
        subject =(TextView)view.findViewById(R.id.subject);
        A=(CheckBox)view.findViewById(R.id.many_A);
        B=(CheckBox)view.findViewById(R.id.many_B);
        C=(CheckBox)view.findViewById(R.id.many_C);
        D=(CheckBox)view.findViewById(R.id.many_D);
        submit=(Button)view.findViewById(R.id.submit);
    }
}
