package com.example.lc.computer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17.
 */
//填空题碎片 Fragment
public class GapfillingFragment extends Fragment {
    TextView subject;
    Button sumbit;
    EditText seleanswer;
    int grade1=0;
    String answer;
    int i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gapfilling_fragment,container,false);
        intview(view);
        grade1=0;
        i=2;
        List<GAPFILLING> gapfillings = DataSupport.where("id = ?","1").find(GAPFILLING.class);
        for(GAPFILLING gapfilling:gapfillings){
            subject.setText("1."+gapfilling.getSubject());
            answer =gapfilling.getAnswer();
        }
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<6){
                    //前4次回答，进入下一题
                    if(seleanswer.getText().toString().trim().equals("")){
                        Toast.makeText(getContext(),"请选择",Toast.LENGTH_SHORT).show();
                    }else {
                        if(seleanswer.getText().toString().trim().equals(answer)){
                            Toast.makeText(getContext(),"答对加5分",Toast.LENGTH_SHORT).show();
                            grade1=grade1+5;
                            seleanswer.setText("");
                        }else {
                            Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                            seleanswer.setText("");
                        }
                        List<GAPFILLING> gapfillings = DataSupport.where("idid = ?",""+ i +"").find(GAPFILLING.class);
                        for (GAPFILLING gapfilling:gapfillings){
                            subject.setText(i+"."+gapfilling.getSubject());
                            answer = gapfilling.getAnswer();
                        }
                        i++;
                    }
                }else {//最后一次回答，进入结算Fragment
                    if(seleanswer.getText().toString().trim().equals(answer)){
                        Toast.makeText(getContext(),"答对加5分",Toast.LENGTH_SHORT).show();
                        grade1=grade1+5;
                        seleanswer.setText("");
                    }else {
                        Toast.makeText(getContext(), "回答错误", Toast.LENGTH_SHORT).show();
                        seleanswer.setText("");
                    }
                    ((MainActivity)getActivity()).setG_grade(grade1);
                    ((MainActivity)getActivity()).Changefragment(new FinishFragment());
                    Log.d("sss", Integer.toString(((MainActivity)getActivity()).getG_grade()));
                }
            }
        });
        return view;
    }
    private void intview(View view){
        subject =(TextView)view.findViewById(R.id.subject);
        sumbit =(Button)view.findViewById(R.id.submit);
        seleanswer =(EditText)view.findViewById(R.id.G_answer);
    }
}
