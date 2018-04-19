package com.example.lc.computer;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/3/17.
 */
//填空题本地SQLLite数据库
public class GAPFILLING extends DataSupport{
    private String subject,answer;
    private int idid;

    public int getIdid() {
        return idid;
    }

    public void setIdid(int idid) {
        this.idid = idid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
