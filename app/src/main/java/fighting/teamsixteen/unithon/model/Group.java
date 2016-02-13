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
    private String creDate;
    private String groupName;
    private String groupFighting;

    public int getIdx(){ return idx;}
    public String getCredate(){ return creDate;}
    public String getGroupName(){ return groupName; }
    public String getGroupFighting(){return groupFighting;}

    private void setIdx(int idx){ this.idx = idx;}
    private void setCreDate(String creDate){this.creDate = creDate;}
    private void setGroupName(String groupName){this.groupName = groupName;}
    private void setGroupFighting(String groupFighting) {this.groupFighting = groupFighting;}


    public void setData(String groupName){
        setGroupName(groupName);
    }
    public void setData(int idx, String groupName){
        setIdx(idx);
        setGroupName(groupName);
    }
    public void setData(int idx, String groupName, String date, String fighting){
        setIdx(idx);
        setGroupName(groupName);
        setCreDate(date);
        setGroupFighting(fighting);
    }
}
