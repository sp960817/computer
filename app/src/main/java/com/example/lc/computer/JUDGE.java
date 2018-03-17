package com.example.lc.computer;

import org.litepal.crud.DataSupport;

/**
 * Created by sp on 2018/3/14.
 */

public class JUDGE extends DataSupport {
    private int idid;
    private String subject;
    private String answer;
    public int getIdid(){
        return idid;
    }
    public void setIdid(int idid){
        this.idid =idid;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject =subject;
    }
    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer =answer;
    }
}
