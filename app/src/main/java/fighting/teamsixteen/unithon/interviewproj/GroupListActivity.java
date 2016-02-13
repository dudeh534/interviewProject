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
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fighting.teamsixteen.unithon.db.DataBase;
import fighting.teamsixteen.unithon.model.Group;
//날짜
public class GroupListActivity extends AppCompatActivity {
    private int n = 2;
    private DataBase db;
    private Group[] groupList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        db = new DataBase(getApplicationContext(), "InterviewDB", null, 1);
        groupList = db.getGroupList();
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        final String strCurDate = CurDateFormat.format(date);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        final List<Recycler_item> items = new ArrayList<>();
        final Recycler_item[] item = new Recycler_item[20];
        item[0] = new Recycler_item(R.drawable.btn_delete, "인턴면접","","");
        for(int i = 0 ; i < groupList.length; i++){
            String creDate = CurDateFormat.format(groupList[i].getCredate());
            item[i+1] = new Recycler_item(R.drawable.btn_delete, groupList[i].getGroupName(),creDate,groupList[i].getGroupFighting());

        }

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position == 0){
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
                    final EditText et1 = (EditText) dialog.findViewById(R.id.editText2);
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
                            item[n] = new Recycler_item(R.drawable.btn_delete, et.getText().toString(), strCurDate, et1.getText().toString());
                            db.createNewFolder(et.getText().toString(), et1.getText().toString());
                            // reset group List
                            groupList = null;
                            groupList = db.getGroupList();
                            n += 1;
                            for (int i = n-1; i < n; i++) items.add(item[i]);
                            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
                        }
                    });

                }else {
                    Intent intent = new Intent(GroupListActivity.this, QuestionListActivity.class);
                    intent.putExtra("GroupIdx",groupList[position-1].getIdx());
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


        for (int i = 0; i < groupList.length+1; i++) items.add(item[i]);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setPadding(3,3,0,0);
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));

    }

}
