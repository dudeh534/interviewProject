package fighting.teamsixteen.unithon.model;

import java.util.Date;

/**
 * Created by Jooyoung on 2016-02-13.
 */
//CREATE TABLE QUESTION(IDX INTEGER  PRIMARY KEY, CREDATE VARCHAR(20) DEFAULT CURRENT_TIMESTAMP, GROUPIDX INTEGER NOT NULL, QUESTIONSTR VARCHAR(255), VOICEPATH VARCHAR(255), CHECKPOINT BOOLEAN)
public class Question {
    private int idx;
    private String creDate;
    private int groupIdx;
    private String question;
    private String voicePath;
    private boolean checkPoint;
    private int answerCount;


    public int getIdx(){ return idx; }
    public String getCreDate() { return creDate; }
    public int getGroupIdx() { return groupIdx; }
    public String getQuestion() { return question; }
    public String getVoicePath(){ return voicePath;}
    public boolean getCheckPoint() { return checkPoint;}
    public int getAnswerCount(){return answerCount;}

    private void setIdx(int idx){ this.idx = idx;}
    private void setCreDate(String creDate){this.creDate = creDate;}
    private void setGroupIdx(int groupIdx){ this.groupIdx = groupIdx;}
    private void setQuestion(String question) { this.question = question;}
    private void setVoicePath(String voicePath) { this.voicePath = voicePath;}
    private void setCheckPoint(boolean checkPoint) { this.checkPoint = checkPoint;}
    public void setAnswerCount(int answerCount){this.answerCount = answerCount;}

    public void setData(int idx,String creDate, int groupIdx, String question, String voicePath){
        setIdx(idx);
        setCreDate(creDate);
        setGroupIdx(groupIdx);
        setQuestion(question);
        setVoicePath(voicePath);
    }

}

