package fighting.teamsixteen.unithon.interviewproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import fighting.teamsixteen.unithon.db.DataBase;

/**
 * Created by ChaeYoungDo on 2016-02-14.
 */
public class Question_Register extends AppCompatActivity {
    ImageView recording;
    private DataBase db;
    private int nowQuestionIdx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_register);

        Intent intent = getIntent();
        nowQuestionIdx = Integer.parseInt(intent.getExtras().getString("GroupIdx"));

        db = new DataBase(getApplicationContext(), "InterviewDB", null, 1);
        recording=(ImageView)findViewById(R.id.recording);
        Button button = (Button) findViewById(R.id.information_btn_toggle12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Question_Register.this,RecordActivity.class);
                startActivity(i);

            }
        });

        Button successButton=(Button)findViewById(R.id.button_success);
        successButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db.createNewQuestion(nowQuestionIdx, "Questionadifoajdsflkadsjf", "Pathakldfjakd;lfja;kdsf");
            }
        });
    }
}
