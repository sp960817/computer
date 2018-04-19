package com.example.lc.computer;
import org.litepal.crud.DataSupport;

import java.util.SimpleTimeZone;

/**
 * Created by sp on 2018/3/13.
 */
//多选本地数据库SQLLite
public class MC extends DataSupport{
    private int idid;
    private String subject;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;
    public int getIdid(){
        return idid;
    }
    public void setIdid(int idid){
        this.idid=idid;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getA(){
        return A;
    }
    public void setA(String A){
        this.A=A;
    }
    public String getB(){
        return B;
    }
    public void setB(String B){
        this.B=B;
    }
    public String getC(){
        return C;
    }
    public void setC(String C){
        this.C=C;
    }
    public String getD(){
        return D;
    }
    public void setD(String D){
        this.D=D;
    }
    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
}

