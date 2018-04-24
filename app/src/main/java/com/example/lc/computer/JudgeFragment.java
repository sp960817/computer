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
 * Created by sp on 2018/3/14.
 */
//多选题碎片Fragment
public class JudgeFragment extends Fragment {
    TextView subject;
    RadioGroup radioGroup;
    RadioButton ok,no;
    Button sumbit;
    int grade1=0;
    String seleanswer="",answer;
    int i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.judge_fragment,container,false);
        intview(view);
        grade1=0;
        i=2;
        List<JUDGE> judges = DataSupport.where("idid = ?","1").find(JUDGE.class);
        for (JUDGE judge:judges){//初始化试题
            subject.setText("1."+judge.getSubject());
            answer = judge.getAnswer();
        }
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//判断按钮逻辑
                RadioButton rb = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
                if (rb!=null){
                    switch (rb.getId()){
                        case R.id.J_yes:
                            seleanswer = "Y";
                            break;
                        case R.id.J_no:
                            seleanswer = "N";
                            break;
                    }
                }else {
                    seleanswer = "";
                }
                if(i<6){//前四题回答后进入下一题
                    if(seleanswer.equals("")){
                        Toast.makeText(getContext(),"请选择",Toast.LENGTH_SHORT).show();
                    }else {
                        if(seleanswer.equals(answer)){
                            Toast.makeText(getContext(),"答对加5分",Toast.LENGTH_SHORT).show();
                            grade1=grade1+5;
                            seleanswer = "";
                            radioGroup.clearCheck();
                        }else {
                            Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                            seleanswer = "";
                            radioGroup.clearCheck();
                        }
                        List<JUDGE> judges = DataSupport.where("idid = ?",""+ i +"").find(JUDGE.class);
                        for (JUDGE judge:judges){
                            subject.setText(i+"."+judge.getSubject());
                            answer = judge.getAnswer();
                        }
                        i++;
                    }
                }else {//最后一题回答后进入填空题
                    if(seleanswer.equals("")){
                        Toast.makeText(getContext(),"请选择",Toast.LENGTH_SHORT).show();
                    }else {
                        if(seleanswer.equals(answer)){
                        Toast.makeText(getContext(),"答对加5分",Toast.LENGTH_SHORT).show();
                        grade1=grade1+5;
                        seleanswer = "";
                        radioGroup.clearCheck();
                    }else {
                        Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                        seleanswer = "";
                        radioGroup.clearCheck();
                    }
                        ((MainActivity)getActivity()).setJ_grade(grade1);
                        ((MainActivity)getActivity()).Changefragment(new GapfillingFragment());
                        Log.d("sss", Integer.toString(((MainActivity)getActivity()).getJ_grade()));
                    }


                }
            }
        });
        return view;
    }
    private void intview(View view){
        subject =(TextView)view.findViewById(R.id.subject);
        ok =(RadioButton)view.findViewById(R.id.J_yes);
        no =(RadioButton)view.findViewById(R.id.J_no);
        sumbit =(Button)view.findViewById(R.id.submit);
        radioGroup =(RadioGroup)view.findViewById(R.id.judge_group);
    }
}
