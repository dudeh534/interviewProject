package fighting.teamsixteen.unithon.interviewproj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class GroupListActivity extends AppCompatActivity {
        private FrameLayout fab;
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
        item[0]=new Recycler_item(R.drawable.button_floating,"인턴면접");
        item[1]=new Recycler_item(R.drawable.button_floating,"동아리면접");
        item[2]=new Recycler_item(R.drawable.button_floating,"개인면접");
        item[3]=new Recycler_item(R.drawable.button_floating,"유니톤면접");
        item[4]=new Recycler_item(R.drawable.button_floating,"공채면접");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));

    }

}
