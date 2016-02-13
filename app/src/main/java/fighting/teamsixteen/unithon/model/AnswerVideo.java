package fighting.teamsixteen.unithon.model;

import java.util.Date;

/**
 * Created by Jooyoung on 2016-02-13.
 */
public class AnswerVideo {
    //IDX INTEGER AUTO_INCREMENT PRIMARY KEY, CREDATE DATE DEFAULT CURRENT_TIMESTAMP, QUESTIONIDX INTEGER NOT NULL, FILEPATH VARCHAR(255) NOT NULL,CREDIT INTEGER, SUMNAILPATH VARCHAR(255)
    private int idx;
    private String curentDate;
    private int questionIdx;
    private String filePath;
    private int credit;
    private String sumnailPath;

    public int getIdx(){ return idx; }
    public String getCurrentDate(){ return curentDate; }
    public int getQuestionIdx(){ return questionIdx; }
    public String getFilePath(){ return filePath; }
    public int getCredit() { return  credit; }
    public String getSumnailPath(){ return  sumnailPath;}

    private void setIdx(int idx) { this.idx = idx;}
    private void setCurrrentDate(String currrentDate) {this.curentDate = currrentDate;}
    private void setQuestionIdx(int questionIdx) {this.questionIdx = questionIdx; }
    private void setFilePath(String filePath) {this.filePath = filePath;}
    private void setCredit(int credit){this.credit = credit;}
    private void setSumnailPath(String sumnailPath) {this.sumnailPath = sumnailPath; }

    public void setData(int idx, String currentDate, int questionIdx, String filePath, int credit, String sumnailPath){
        setIdx(idx);
        setCurrrentDate(currentDate);
        setQuestionIdx(questionIdx);
        setFilePath(filePath);
        setCredit(credit);
        setSumnailPath(sumnailPath);
    }
}
