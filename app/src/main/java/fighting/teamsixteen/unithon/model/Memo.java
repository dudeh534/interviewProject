package fighting.teamsixteen.unithon.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Jooyoung on 2016-02-13.
 */
public class Memo {
    private int idx;
    private Date creDate;
    private int answeridx;
    private String memoTime;
    private String memoString;


    public int getIdx(){return idx; }
    public Date getCreDate(){ return creDate; }
    public int getAnswer(){ return answeridx; }
    public String getMemoTime(){ return memoTime; }
    public String getMemoString(){return memoString; }

    private void setIdx(int idx){this.idx = idx;}
    private void setCreDate(Date creDate){this.creDate = creDate; }
    private void setAnswer(int answeridx){this.answeridx = answeridx; }
    private void setMemoTime(String memoTime){this.memoTime = memoTime; }
    private void setMemoString(String memoString){this.memoString = memoString;}

    public void setData(int idx, Date creDate, int answeridx, String memoTime, String memoString){
        setIdx(idx);
        setCreDate(creDate);
        setAnswer(answeridx);
        setMemoTime(memoTime);
        setMemoString(memoString);
    }
}
