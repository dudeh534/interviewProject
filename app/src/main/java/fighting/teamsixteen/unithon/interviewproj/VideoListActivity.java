package fighting.teamsixteen.unithon.interviewproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        Button goNext = (Button)findViewById(R.id.btn_go_videomake);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(VideoListActivity.this, VideoMakeActivity.class);
                startActivity(i);
            }
        });


        Button goNext2 = (Button)findViewById(R.id.btn_go_videoview);
        goNext2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(VideoListActivity.this, VideoViewActivity.class);
                startActivity(i);
            }
        });
    }

}
