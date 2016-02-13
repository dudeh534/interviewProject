package fighting.teamsixteen.unithon.interviewproj;

import android.app.AlertDialog;
import android.content.Intent;
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

public class GroupListActivity extends AppCompatActivity {
        private FrameLayout fab;

        private boolean opacity = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        fab = (FrameLayout)findViewById(R.id.fab);
        List<Recycler_item> items=new ArrayList<>();
        Recycler_item[] item=new Recycler_item[5];
        item[0]=new Recycler_item(R.drawable.btn_delete,"인턴면접");
        item[1]=new Recycler_item(R.drawable.btn_delete,"동아리면접");
        item[2]=new Recycler_item(R.drawable.btn_delete,"개인면접");
        item[3]=new Recycler_item(R.drawable.btn_delete,"유니톤면접");
        item[4]=new Recycler_item(R.drawable.btn_delete,"공채면접");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.popup_gonggu_join, null);
                AlertDialog.Builder buider = new AlertDialog.Builder(GroupListActivity.this); //AlertDialog.Builder 객체 생성
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅 (Customize)

                //설정한 값으로 AlertDialog 객체 생성
                final AlertDialog dialog = buider.create();

                //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.setCanceledOnTouchOutside(false);//없어지지 않도록 설정

                //Dialog 보이기
                dialog.show();
                final EditText et = (EditText) dialog.findViewById(R.id.editText);
                final ImageButton check = (ImageButton) dialog.findViewById(R.id.join_popup_check);
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
                        if(!s.toString().isEmpty()){
                            check.getBackground().setAlpha(200);
                            check.setEnabled(true);
                        }else{
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
        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(GroupListActivity.this, QuestionListActivity.class);
                //intent.putExtra("")
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));

    }

}
