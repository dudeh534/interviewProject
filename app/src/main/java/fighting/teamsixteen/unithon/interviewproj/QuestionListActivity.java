package fighting.teamsixteen.unithon.interviewproj;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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
        item[0] = new Recycler_Item_question("Selfintroduce","adfasdf","1");
        item[1] = new Recycler_Item_question("Selfintroduce","adfasdf","2");
        item[2] = new Recycler_Item_question("Selfintroduce","adfasdf","3");
        for (int i = 0; i < 3 ; i++) items.add(item[i]);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new RecyclerAdapter_question(getApplicationContext(), items, R.layout.activity_question_list));
        fab = (FrameLayout) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.popup_question, null);
                AlertDialog.Builder buider = new AlertDialog.Builder(QuestionListActivity.this); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅 (Customize)

                //설정한 값으로 AlertDialog 객체 생성
                final AlertDialog dialog = buider.create();

                //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.setCanceledOnTouchOutside(false);//없어지지 않도록 설정
                //Dialog 보이기
                dialog.show();
                final EditText et = (EditText) dialog.findViewById(R.id.q_editText);
                final ImageButton check = (ImageButton) dialog.findViewById(R.id.q_join_popup_check);
                check.getBackground().setAlpha(51);
                check.setEnabled(false);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!s.toString().isEmpty()) {
                            check.getBackground().setAlpha(200);
                            check.setEnabled(true);
                        } else {
                            check.getBackground().setAlpha(51);
                            check.setEnabled(false);
                        }
                    }
                });

                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

}
