package ex;

/**
 * Created by sp on 2018/4/2.
 */
//成绩数据组
public class Fruit {
    private String testid,g1,g2,g3,g4,gg;
    public Fruit(String testtid,String g1,String g2,String g3,String g4,String gg){
        this.g1=g1;
        this.testid=testid;
        this.g2=g2;
        this.g3=g3;
        this.g4=g4;
        this.gg=gg;
    }

    public String getG1() {
        return g1;
    }

    public String getG2() {
        return g2;
    }

    public String getG3() {
        return g3;
    }

    public String getG4() {
        return g4;
    }

    public String getGg() {
        return gg;
    }

    public String getTestid() {
        return testid;
    }
}
