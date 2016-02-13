package fighting.teamsixteen.unithon.interviewproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ChaeYoungDo on 2016-02-14.
 */
public class Question_Register extends AppCompatActivity {
    ImageView recording;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_register);
        recording=(ImageView)findViewById(R.id.recording);
        recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Question_Register.this,RecordActivity.class);
                startActivity(i);
            }
        });
    }
}
