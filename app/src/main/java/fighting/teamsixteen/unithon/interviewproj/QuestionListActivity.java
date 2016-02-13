package fighting.teamsixteen.unithon.interviewproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import fighting.teamsixteen.unithon.db.DataBase;
import fighting.teamsixteen.unithon.model.Question;

public class QuestionListActivity extends AppCompatActivity {
    private FrameLayout fab;
    private int n = 3;
    private int nowQuestionIdx;         // save now question idx
    private Question[] questions;
    private DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Intent intent = getIntent();
        nowQuestionIdx = Integer.parseInt(intent.getExtras().getString("GroupIdx"));

        db = new DataBase(getApplicationContext(), "InterviewDB", null, 1);
        questions = db.getQuestionList(nowQuestionIdx);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        final List<Recycler_Item_question> items = new ArrayList<>();
        final Recycler_Item_question[] item = new Recycler_Item_question[20];
        if(questions[0] != null) {
            int arrSize = 0;
            for (int i = 0; i < 20; i++) {
                if(questions[i] == null){
                    break;
                }else {
                    String formatStr = "해당 질문에 대한 답변이 " + questions[i].getAnswerCount() + "개 있습니다.";
                    item[i] = new Recycler_Item_question(questions[i].getQuestion(), formatStr, "" + (i + 1));
                    arrSize++;
                }
            }
            for (int i = 0; i < arrSize; i++) items.add(item[i]);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
        recyclerView.setAdapter(new RecyclerAdapter_question(getApplicationContext(), items, R.layout.activity_question_list));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent i=new Intent(QuestionListActivity.this,Vid)
//                questions[position].getIdx()
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        fab = (FrameLayout) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(QuestionListActivity.this, Question_Register.class);
                intent2.putExtra("GroupIdx", ""+nowQuestionIdx);
                startActivity(intent2);
                finish();
            }
        });

        Button back = (Button) findViewById(R.id.information_btn_toggle122);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
