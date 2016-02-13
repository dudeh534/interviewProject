package fighting.teamsixteen.unithon.interviewproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
<<<<<<< HEAD
import android.widget.ImageView;
=======
import android.widget.Button;
>>>>>>> 98d8f25624ad4398e7eb64c8617b721c5fa9d64a

/**
 * Created by ChaeYoungDo on 2016-02-14.
 */
public class Question_Register extends AppCompatActivity {
    ImageView recording;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_register);
<<<<<<< HEAD
        recording=(ImageView)findViewById(R.id.recording);
        recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Question_Register.this,RecordActivity.class);
                startActivity(i);
=======
        Button button = (Button) findViewById(R.id.information_btn_toggle12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
>>>>>>> 98d8f25624ad4398e7eb64c8617b721c5fa9d64a
            }
        });
    }
}
