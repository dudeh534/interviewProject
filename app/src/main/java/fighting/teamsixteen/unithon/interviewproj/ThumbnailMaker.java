package fighting.teamsixteen.unithon.interviewproj;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fighting.teamsixteen.unithon.db.DataBase;
import fighting.teamsixteen.unithon.model.AnswerVideo;
import fighting.teamsixteen.unithon.model.Question;

/**
 * Created by Kim GyuHwan on 2016-02-13.
 */
public class ThumbnailMaker {
    //String Path[];
    Bitmap Thumbnail[];
   // String Time[];
    Boolean Star[];
    int Index[];
    int size;
    AnswerVideo[] answers;

    private ArrayList<AnswerVideo> answersList;
    void UpdatedThumbnail(Context mContext, int group_id) {
        //  Path=getPath();

        DataBase db = new DataBase(mContext, "InterviewDB", null, 1);
        answers = db.getAnswerVideo(group_id);
        size = answers.length;
     //   Path = new String[size];

        Thumbnail = new Bitmap[size];
     //   Time = new String[size];
        Star = new Boolean[size];
        Index = new int[size];
        for (int i = 0; i < size; i++) {

            if (answers[i] != null) {

        //        Path[i] = answers[i].getFilePath();
                Thumbnail[i] = ThumbnailUtils.createVideoThumbnail(answers[i].getFilePath(), MediaStore.Video.Thumbnails.MINI_KIND);
                //Star[i] = answers[i].getCredit();
                Star[i] = true;
                Index[i] = answers[i].getIdx();
            }
        }

    }

    public ListAdapter getVideoAdapter(Context context) {
        MyArrayAdapter myArrayAdapter = new MyArrayAdapter(context);
        return myArrayAdapter;
    }

    class MyArrayAdapter extends ArrayAdapter {
        Context context;

        MyArrayAdapter(Context context) {
//            super(context, R.layout.thumbnail, Path);

            super(context, R.layout.thumbnail, R.id.thumnail_nothing);
            // instance 변수(this.context)를 생성자 호출시 전달받은 지역 변수(context)로 초기화.
            this.context = context;

            fillAdapter();
        }

        View makethumb(int idx) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View ret = (View) inflater.inflate(R.layout.thumbnail, null);
            ImageView Thumbnail_IMG = (ImageView) ret.findViewById(R.id.thumbnail_BitMap);
            ImageView Thumbnail_star = (ImageView) ret.findViewById(R.id.thumbnail_star);
            TextView Thumbnail_time = (TextView) ret.findViewById(R.id.thumbnail_time);
            Thumbnail_IMG.setImageBitmap(Thumbnail[idx]);
            if (Star[idx]) {
                Thumbnail_star.setImageResource(R.drawable.thumbnail_star);
            } else {
                Thumbnail_star.setImageResource(R.drawable.question_record_start);

            }
            Thumbnail_time.setText(answers[idx].getCurrentDate());
            ret.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show();
//Index[idx] 이게 클릭된 영상의 idx
                }
            });
            return ret;
        }

        void fillAdapter() {
            for (int i = 0; i < size; i++)
                super.add(makethumb(i));
        }
    }

    ;

}