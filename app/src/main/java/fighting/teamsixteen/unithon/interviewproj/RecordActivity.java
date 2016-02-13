package fighting.teamsixteen.unithon.interviewproj;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class RecordActivity extends Activity {
    public MediaRecorder mrec;
    private static final int STATE_NONE=0;
    private static final int STATE_RECORDING=1;
    private static final int STATE_RECORED=2;
    private static final int STATE_PLAYING=3;

    MediaPlayer mediaPlayer=null;
    File audiofile;
    int recordState=STATE_NONE;
    private static final String TAG = "SoundRecordingDemo";
    ImageButton CircleBtn;
    ImageButton RestartBtn;
    ImageButton FinishBtn;
    ImageButton CancelButton;
    Timer timer;
    TimerTask timerTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        CancelButton=(ImageButton)findViewById(R.id.CancelBtn);
        CircleBtn=(ImageButton)findViewById(R.id.CircleBtn);
        RestartBtn=(ImageButton)findViewById(R.id.restartBtn);
        FinishBtn=(ImageButton)findViewById(R.id.FinishBtn);


        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        CircleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (recordState) {
                    case STATE_NONE:
                        recordState=STATE_RECORDING;
                        Toast.makeText(getApplicationContext(),"녹음이 시작되었습니다.",Toast.LENGTH_LONG);
                        CircleBtn.setBackgroundResource(R.drawable.question_record_pause);
                        try {
                            startRecording();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case STATE_RECORDING:
                        recordState=STATE_RECORED;
                        CircleBtn.setBackgroundResource(R.drawable.question_record_play);
                        Toast.makeText(getApplicationContext(),"녹음이 중지되었습니다.",Toast.LENGTH_SHORT);
                        stopRecording();
                        break;
                    case STATE_RECORED:
                        CircleBtn.setBackgroundResource(R.drawable.question_record_pause);
                        Toast.makeText(getApplicationContext(),"녹음이 재생되었습니다.",Toast.LENGTH_SHORT);
                        recordState=STATE_PLAYING;
                        playRecording();
                        break;
                    case STATE_PLAYING:
                        Toast.makeText(getApplicationContext(), "녹음파일의 재생이 중지되었습니다.", Toast.LENGTH_SHORT);
                        CircleBtn.setBackgroundResource(R.drawable.question_record_play);
                        recordState=STATE_RECORED;
                        break;
                }
            };
        });
        RestartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recordState!=STATE_RECORDING)
                {
                    Toast.makeText(getApplicationContext(),"녹음파일이 초기화되었습니다",Toast.LENGTH_SHORT);
                    CircleBtn.setBackgroundResource(R.drawable.question_record_start);
                    recordState=STATE_NONE;
                    audiofile=null;
                }
            }
        });
        FinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"녹음이 저장되었습니다.",Toast.LENGTH_SHORT);
                    if(audiofile!=null)
                      processaudiofile();
                    finish();

            }
        });
    }

    protected void startRecording() throws IOException
    {
        mrec=new MediaRecorder();
        mrec.setAudioSource(MediaRecorder.AudioSource.MIC);
                mrec.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mrec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                if(audiofile == null)
                {
                    File sampleDir = Environment.getExternalStorageDirectory();
                    try
                    {
                        audiofile = File.createTempFile("ibm", ".3gp", sampleDir);
                    }catch (IOException e)
                    {
                Log.e(TAG, "sdcard access error!");
                return;
            }
        }
        mrec.setOutputFile(audiofile.getAbsolutePath());
        mrec.prepare();
        mrec.start();
    }

    protected void stopRecording()
    {
        if(mrec==null) return;
        mrec.stop();
        mrec.release();
        mrec=null;
    }

    protected void processaudiofile()
    {
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int)(current/1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
        ContentResolver contentResolver = getContentResolver();
        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
    }

    protected void playRecording()
    {
        if(audiofile==null) return;
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        try {

            // 오디오를 플레이 하기위해 MediaPlayer 객체 player를 생성한다.
            mediaPlayer = new MediaPlayer ();

            // 재생할 오디오 파일 저장위치를 설정
            mediaPlayer.setDataSource(audiofile.getPath());
            // 웹상에 있는 오디오 파일을 재생할때
            // player.setDataSource(Audio_Url);

            // 오디오 재생준비,시작
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    CircleBtn.setBackgroundResource(R.drawable.question_record_play);
                    recordState=STATE_RECORED;
                }
            });
        } catch (Exception e) {
            Log.e("SampleAudioRecorder", "Audio play failed.", e);
        }


    }

}
