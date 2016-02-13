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

public class QuestionListActivity extends AppCompatActivity {
    private FrameLayout fab;
    private int n = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        final List<Recycler_Item_question> items = new ArrayList<>();
        final Recycler_Item_question[] item = new Recycler_Item_question[20];
        item[0] = new Recycler_Item_question("자기 소개 5분동안 하기","화이팅","1");
        item[1] = new Recycler_Item_question("자기 소개 5분동안 하기","화이팅","2");
        item[2] = new Recycler_Item_question("자기 소개 5분동안 하기","화이팅","3");
        for (int i = 0; i < 3 ; i++) items.add(item[i]);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new RecyclerAdapter_question(getApplicationContext(), items, R.layout.activity_question_list));
        fab = (FrameLayout) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionListActivity.this, Question_Register.class);
                startActivity(intent);
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
