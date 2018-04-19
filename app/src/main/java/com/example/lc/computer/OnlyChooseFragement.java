package com.example.lc.computer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by sp on 2018/3/12.
 */
//单选题Fragment
public class OnlyChooseFragement extends Fragment {
    TextView subject;
    Button submit;
    RadioButton A,B,C,D;
    int grade1=0;
    String seleanswer="",answer;
    RadioGroup radioGroup;
    int i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.onlychoose_fragment,container,false);
        intview(view);
        grade1=0;
        i=2;
        List<OC> ocs = DataSupport.where("idid = ?","1").find(OC.class);
        for(OC oc:ocs){//初始化试题
                subject.setText("1."+oc.getSubject());
                A.setText(oc.getA());
                B.setText(oc.getB());
                C.setText(oc.getC());
                D.setText(oc.getD());
                answer =oc.getAnswer();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//单选逻辑
                RadioButton rb = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
                if (rb != null) {
                    switch (rb.getId()) {
                        case R.id.only_A:
                            seleanswer = "A";
                            break;
                        case R.id.only_B:
                            seleanswer = "B";
                            break;
                        case R.id.only_C:
                            seleanswer = "C";
                            break;
                        case R.id.only_D:
                            seleanswer = "D";
                            break;
                    }
                } else {
                    seleanswer = "";
                }
                if (i < 6) {//前四题回答完进入下一题
                    if (seleanswer.equals("")) {
                        Toast.makeText(getContext(), "请选择", Toast.LENGTH_SHORT).show();
                    } else {
                        if (answer.equals(seleanswer)) {
                            Toast.makeText(getContext(), "答对加5分", Toast.LENGTH_SHORT).show();
                            grade1 = grade1 + 5;
                            radioGroup.clearCheck();
                            seleanswer = "";
                        } else {
                            Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                            seleanswer = "";
                            radioGroup.clearCheck();
                        }
                        List<OC> ocs = DataSupport.where("idid = ?", "" + i + "").find(OC.class);
                        for (OC oc : ocs) {
                            subject.setText(i + "." + oc.getSubject());
                            A.setText(oc.getA());
                            B.setText(oc.getB());
                            C.setText(oc.getC());
                            D.setText(oc.getD());
                            answer = oc.getAnswer();
                        }
                        i++;
                    }

                } else {//最后一题回答完进入多选题Fragment
                    if (answer.equals(seleanswer)) {
                        Toast.makeText(getContext(), "答对加5分", Toast.LENGTH_SHORT).show();
                        grade1 = grade1 + 5;
                        radioGroup.clearCheck();
                    } else {
                        Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                        radioGroup.clearCheck();
                    }
                    ((MainActivity) getActivity()).setO_grade(grade1);
                    ((MainActivity) getActivity()).Changefragment(new ManyChooseFragment());
                    Log.d("sss", Integer.toString(((MainActivity) getActivity()).getO_grade()));
                }
            }
        });
        return view;
    }
    private void intview(View view) {//初始化控件
        subject = (TextView)view.findViewById(R.id.subject);
        A =(RadioButton)view.findViewById(R.id.only_A);
        B =(RadioButton)view.findViewById(R.id.only_B);
        C =(RadioButton)view.findViewById(R.id.only_C);
        D =(RadioButton)view.findViewById(R.id.only_D);
        submit =(Button)view.findViewById(R.id.submit);
        radioGroup =(RadioGroup) view.findViewById(R.id.only_group);
    }

}
