package fighting.teamsixteen.unithon.interviewproj;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import fighting.teamsixteen.unithon.util.HorizontalListView;

public class VideoViewActivity extends AppCompatActivity {

    VideoView videoview;
    TextView testTv;
    ImageView btn_addmemo;


    MediaController mediaController;
HorizontalListView videoview_horizontallist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        mediaController = new MediaController(this);

        videoview_horizontallist=(HorizontalListView)findViewById(R.id.videoview_horizontallist);
        ThumbnailMaker thumbnailMaker=new ThumbnailMaker();

        videoview_horizontallist.setAdapter(thumbnailMaker.getVideoAdapter(VideoViewActivity.this));

        videoview = (VideoView) findViewById(R.id.videoview);
        btn_addmemo = (ImageView) findViewById(R.id.btn_addmemo);
        btn_addmemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoview.pause();
                String tmp = testTv.getText().toString() + "\n" + videoview.getCurrentPosition() / 1000;
                testTv.setText(tmp);
                mediaController.show();
            }
        });

        //videoview.setVideoPath();
        String PATH = "mnt/sdcard/videooutput.mp4";
        videoview.setVideoPath(PATH);
//        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.aaaa));

//        mediaController.setAnchorView(videoview);
        videoview.setMediaController(mediaController);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                videoview.start();
                testTv.setText("\n비디오 총시간 : " + videoview.getDuration() / 1000);//비디오 총시간?
            }
        });
    }


public class VideoListAdapter extends BaseAdapter{

    Context mContext;

    private LayoutInflater inflater = null;

    public VideoListAdapter(Context mContext){
        this.mContext=mContext;
//        inflater

    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
}
