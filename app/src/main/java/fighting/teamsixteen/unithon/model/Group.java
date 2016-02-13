package fighting.teamsixteen.unithon.model;

import java.util.Date;

/**
 * Created by Jooyoung on 2016-02-13.
 */
public class Group {
    public Group(){};
    public Group(String groupName){
        setGroupName(groupName);
    }
    private int idx;
    private Date creDate;
    private String groupName;

    public int getIdx(){ return idx;}
    public Date getCredate(){ return creDate;}
    public String getGroupName(){ return groupName; }

    private void setIdx(int idx){ this.idx = idx;}
    private void setCreDate(Date creDate){this.creDate = creDate;}
    private void setGroupName(String groupName){this.groupName = groupName;}


    public void setData(String groupName){
        setGroupName(groupName);
    }
    public void setData(int idx, String groupName){
        setIdx(idx);
        setGroupName(groupName);
    }
    public void setData(int idx, String groupName, Date date){
        setIdx(idx);
        setGroupName(groupName);
        setCreDate(date);
    }
}
