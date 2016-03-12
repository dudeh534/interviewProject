package fighting.teamsixteen.unithon.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import fighting.teamsixteen.unithon.model.AnswerVideo;
import fighting.teamsixteen.unithon.model.Group;
import fighting.teamsixteen.unithon.model.Memo;
import fighting.teamsixteen.unithon.model.Question;

/**
 * Created by Jooyoung on 2016-02-13.
 */
public class DataBase extends SQLiteOpenHelper{
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create new Database
        String query = "CREATE TABLE FOLDER(IDX INTEGER PRIMARY KEY, CREDATE VARCHAR(20), GROUPNAME VARCHAR(255) NOT NULL, GROUPFIGHTING VARCHAR(255))";
        db.execSQL(query);
        String query2 = "CREATE TABLE QUESTION(IDX INTEGER  PRIMARY KEY, CREDATE VARCHAR(20), GROUPIDX INTEGER NOT NULL, QUESTIONSTR VARCHAR(255), VOICEPATH VARCHAR(255), CHECKPOINT BOOLEAN)";
        db.execSQL(query2);
        String query3 = "CREATE TABLE ANSWER(IDX INTEGER PRIMARY KEY, CREDATE VARCHAR(20), QUESTIONIDX INTEGER NOT NULL, FILEPATH VARCHAR(255) NOT NULL,CREDIT INTEGER, SUMNAILPATH VARCHAR(255))";
        db.execSQL(query3);
        String query4 = "CREATE TABLE MEMO(IDX INTEGER PRIMARY KEY, CREDATE VARCHAR(20), ANSWERIDX INTEGER NOT NULL, MEMOTIME VARCHAR(30), MEMOSTRING VARCHAR(255))";
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();

    }

    public void createNewFolder(String groupName, String fighting) {
        long now = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        final String strCurDate = CurDateFormat.format(date);
        String query = "INSERT INTO FOLDER(GROUPNAME, GROUPFIGHTING, CREDATE) VALUES(\"" + groupName + "\", \"" + fighting + "\", \"" + strCurDate + "\")";
        insert(query);
    }


    public Group[] getGroupList(){
        Group[] reGrup = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM FOLDER ORDER BY CREDATE DESC", null);
        reGrup = new Group[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getInt(0);
            String date = cursor.getString(1);
            String groupname = cursor.getString(2);
            String groupFighting = cursor.getString(3);
            reGrup[i] = new Group();
            reGrup[i].setData(idx, groupname, date, groupFighting);
            i++;
        }
        return  reGrup;
    }

    public void createNewQuestion(int groupIdx, String questionStr, String voicePath){
        long now = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        final String strCurDate = CurDateFormat.format(date);
        String query = "INSERT INTO QUESTION(GROUPIDX, QUESTIONSTR, VOICEPATH, CREDATE) VALUES("+groupIdx + ", \""+ questionStr + "\", \"" + voicePath +"\", \"" + strCurDate + "\")";
        insert(query);

    }

    public Question[] getQuestionList(int groupIdx){
        Question[] reQ = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT *, (SELECT COUNT(*) FROM ANSWER WHERE ANSWER.QUESTIONIDX = QUESTION.IDX) AS CNT FROM QUESTION WHERE GROUPIDX= " + groupIdx + " ORDER BY CREDATE DESC";
        Cursor cursor = db.rawQuery(query,null);
        reQ = new Question[cursor.getCount()];
        if(cursor.getCount() == 0){
            return null;
        }
        int i = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getInt(0);
            String creDate = cursor.getString(1);
            int groupidx = cursor.getInt(2);
            String question = cursor.getString(3);
            String voicePath = cursor.getString(4);
            int answerCnt = cursor.getInt(5);
            reQ[i] = new Question();
            reQ[i].setData(idx, creDate, groupidx,question,voicePath);
            reQ[i].setAnswerCount(answerCnt);
            i++;
        }
        return reQ;
    }

    public void createNewAnswerVideo(int questionIdx, String filePath, int credit, String sumnailPath ){
        long now = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        final String strCurDate = CurDateFormat.format(date);
        String query = "INSERT INTO ANSWER(QUESTIONIDX, FILEPATH, CREDIT, SUMNAILPATH, CREDATE) VALUES(" + questionIdx + ", \"" + filePath + "\", "+credit + ", \"" + sumnailPath + "\", \"" + strCurDate + "\")";
        insert(query);
    }

    public AnswerVideo[] getAnswerVideo(int questionIdx){
        AnswerVideo[] reAnswer = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM ANSWER WHERE QUESTIONIDX= " + questionIdx + " ORDER BY CREDATE DESC";
        Cursor cursor = db.rawQuery(query,null);
        reAnswer = new AnswerVideo[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getInt(0);
            String creDate = cursor.getString(1);
            int questionidx = cursor.getInt(2);
            String filePath = cursor.getString(3);
            int credit = cursor.getInt(4);
            String sumnailPath = cursor.getString(5);
            reAnswer[i] = new AnswerVideo();
            reAnswer[i].setData(idx, creDate, questionidx,filePath,credit,sumnailPath);
            i++;
        }
        return reAnswer;
    }

    public void createNewMemo(int answerIdx, String memoTime, String memoString){
        long now = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        final String strCurDate = CurDateFormat.format(date);
        String query = "INSERT INTO MEMO(ANSWERIDX, MEMOTIME, MEMOSTRING, CREDATE) VALUES("+answerIdx + ", \"" + memoTime + "\", \"" + memoString +"\", \"" + strCurDate + "\")";
        insert(query);
    }


    public Memo[] getMemoList(int answerIdx){
        Memo[] reMemo = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM MEMO WHERE ANSWERIDX= " + answerIdx + " ORDER BY CREDATE DESC";
        Cursor cursor = db.rawQuery(query,null);
        reMemo = new Memo[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            int idx = cursor.getInt(0);
            String creDate = cursor.getString(1);
            int answeridx = cursor.getInt(2);
            String memoTime = cursor.getString(3);
            String memoString = cursor.getString(4);
            reMemo[i] = new Memo();
            reMemo[i].setData(idx, creDate, answeridx,memoTime,memoString);
            i++;
        }
        return reMemo;
    }

    public String printData(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from FOLDER",null);
        while(cursor.moveToNext()){
            str += cursor.getInt(0);
            str += ")";
            str += cursor.getString(2);
            str += "\t";
        }
        return str;
    }

}
































