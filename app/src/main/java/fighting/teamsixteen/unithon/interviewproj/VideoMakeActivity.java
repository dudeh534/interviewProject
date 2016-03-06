package fighting.teamsixteen.unithon.interviewproj;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.jar.Manifest;

import fighting.teamsixteen.unithon.db.DataBase;
import fighting.teamsixteen.unithon.util.HorizontalListView;

public class VideoMakeActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    // 아웃풋 파일 경로
    private static final String OUTPUT_FILE = "/sdcard";
    // 녹화 시간 - 10초
    private static final int RECORDING_TIME = 120000;
    private static final int STATE_NOT_RECORD=0;
    private static final int STATE_RECORDING=1;
    private static final int CAMERA_BACK = 0 ;
    private static final int CAMERA_FRONT = 1;




    int currentState=STATE_NOT_RECORD;
    Camera camera;
    int currentCameraId = 1;
    TextureView mTexture;
    //    RelativeLayout cameraLayout;
    private Camera mCamera;
    MediaRecorder recorder;

    boolean isrecording = false;

    String recordname = "";
    int question_idx;
    int group_idx;
    HorizontalListView videoview_horizontallist;


    protected void onPause() {
        // TODO Auto-generated method stub
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            Log.d("DEBUG_TAG", "releaseCamera -- done");
        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_make);



        if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Camera permission is not granted", Toast.LENGTH_SHORT).show();


        }else if(ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "WRITE_EXTERNAL_STORAGE permission is not granted", Toast.LENGTH_SHORT).show();
        } else {
            question_idx = getIntent().getIntExtra("Question_idx", 0);
            group_idx = getIntent().getIntExtra("Group_idx", 0);

            videoview_horizontallist = (HorizontalListView) findViewById(R.id.videoview_horizontallist);
            ThumbnailMaker thumbnailMaker = new ThumbnailMaker();
            thumbnailMaker.UpdatedThumbnail(VideoMakeActivity.this, group_idx);
            videoview_horizontallist.setAdapter(thumbnailMaker.getVideoAdapter(VideoMakeActivity.this));

            mTexture = (TextureView) findViewById(R.id.textureView1);
            mTexture.setSurfaceTextureListener(this);
            final ImageView frontback = (ImageView) findViewById(R.id.frontback);
            frontback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeCamera();
                }
            });
            final ImageView record_btn = (ImageView) findViewById(R.id.record_btn);
            record_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (!isrecording) {
                        if (currentState == STATE_NOT_RECORD) {
                            currentState = STATE_RECORDING;
                            Log.e("CAM TEST", "REC START!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                            if (mTexture == null) {
                                Log.e("CAM TEST", "View Err!!!!!!!!!!!!!!!");
                            }
                            isrecording = true;
                            recordname = "/SI" + System.currentTimeMillis() + ".mp4";

                            beginRecording(mTexture);
                            Log.d("Name", recordname);
                            record_btn.setBackgroundResource(R.drawable.question_record_ing);
                        }
                    } else {

                        isrecording = false;
                        // 레코더 객체가 존재할 경우 이를 스톱시킨다
                        if (currentState == STATE_RECORDING) {

                            currentState = STATE_NOT_RECORD;
                            if (recorder != null) {
                                Log.e("CAM TEST", "CAMERA STOP!!!!!");
                                recorder.release();
                                //                       recorder.stop();
                                recorder = null;
                            }
                            // 프리뷰가 없을 경우 다시 가동 시킨다
                            if (mCamera != null) {
                                Log.e("CAM TEST", "Preview Restart!!!!!");
                                // 프리뷰 다시 설정{
                                mCamera.stopPreview();
                                mCamera.release();
                                mCamera = null;
                                setCameraPreview(mTexture, currentCameraId);
                            }
                        }
                        record_btn.setBackgroundResource(R.drawable.question_record_start);
                        DataBase db = new DataBase(getApplicationContext(), "InterviewDB", null, 1);
                        db.createNewAnswerVideo(question_idx, OUTPUT_FILE + recordname, 0, "");
                    }
                }
            });
        }



    }

    private void startCamera(int facing) {

        Log.e("STARTRTRR",String.valueOf(facing));
        try
        {
            mCamera = Camera.open(facing);
            Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

            mTexture.setLayoutParams(new FrameLayout.LayoutParams(
                    previewSize.width, previewSize.height, Gravity.CENTER));
            mCamera.setPreviewTexture(mTexture.getSurfaceTexture());
            mCamera.startPreview();
        }
        catch(RuntimeException e)
        {
            Log.d("TAG",e.toString());
        }catch (IOException e){
            Log.d("TAG",e.toString());
        }
        finally {
            closeCamera();
        }

    }

    private void closeCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    public void changeCamera() {
        closeCamera();

        if (currentCameraId == CAMERA_BACK) {
            currentCameraId = CAMERA_FRONT;
            startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else {
            currentCameraId = CAMERA_FRONT;
            startCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
        }
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1, int arg2) {
        closeCamera();
        startCamera(currentCameraId);
        setCameraPreview(mTexture, currentCameraId);
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        mTexture.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));

        try {
            mCamera.setPreviewTexture(arg0);
            mCamera.startPreview();
            mTexture.setAlpha(1.0f);
            mTexture.setRotation(getCameraDegress());//90.0f);//
        } catch (IOException t) {
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
                                            int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
        // TODO Auto-generated method stub
    }


    // 카메라 프리뷰를 설정한다
    private void setCameraPreview(TextureView textureView, int frontback) {
        try {
            mCamera = Camera.open(frontback);//0이 후면  1이 전면

            Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

            mTexture.setLayoutParams(new FrameLayout.LayoutParams(
                    previewSize.width, previewSize.height, Gravity.CENTER));

            try {
                mCamera.setPreviewTexture(textureView.getSurfaceTexture());
            } catch (IOException t) {
            }
            mCamera.startPreview();
            mTexture.setAlpha(1.0f);
            mTexture.setRotation(getCameraDegress());//90.0f);//
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void beginRecording(TextureView textureView) {
        // 레코더 객체 초기화
        Log.e("CAM TEST", "#1 Begin REC!!!");
        if (recorder != null) {
            recorder.stop();
            recorder.release();
        }
        String state = android.os.Environment.getExternalStorageState();
        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
            Log.e("CAM TEST", "I/O Exception");
        }

        Log.e("CAM TEST", "#2 Create File!!!");
        File outFile = new File(OUTPUT_FILE + recordname);
        if (outFile.exists()) {
            outFile.delete();
        }
        Log.e("CAM TEST", "#3 Release Camera!!!");
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            Log.e("CAM TEST", "#3 Release Camera  _---> OK!!!");
        }

        try {
            recorder = new MediaRecorder();

            camera = Camera.open(currentCameraId);
            camera.unlock();
            recorder.setCamera(camera);

            // Video/Audio 소스 설정
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            if (currentCameraId == 0) {
                recorder.setVideoSize(640, 480);
                recorder.setVideoFrameRate(15);
                recorder.setVideoEncodingBitRate(3 * 1000 * 1000);
              //  recorder.setOrientationHint(getCameraDegress());//90);
            } else {
                recorder.setVideoSize(320, 240);//640, 480);
                recorder.setVideoFrameRate(5);
                recorder.setVideoEncodingBitRate(1 * 1000 * 1000);
             //   recorder.setOrientationHint((getCameraDegress() + 180)%360);//90);
            }
            // Video/Audio 인코더 설정
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // 프리뷰를 보여줄 서피스 설정
            recorder.setPreviewDisplay(new Surface(textureView.getSurfaceTexture()));

            // 녹화할 대상 파일 설정
            String tmp=OUTPUT_FILE + recordname;
            recorder.setOutputFile(tmp);
            Log.d("Namerecord1",OUTPUT_FILE+recordname);
            Log.d("Namerecord2",recordname+' ');
            recorder.setMaxDuration(2 * 60 * 1000); // Set max duration 60 sec.
            recorder.setMaxFileSize(10 * 1000 * 1000); // Set max file size 5M


            recorder.prepare();
            recorder.start();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("CAM TEST", "Error Occur???!!!");
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(mTexture!=null){
                setCameraPreview(mTexture,currentCameraId);
                mTexture.setSurfaceTextureListener(this);
            }else{
                Log.e("onResume","mTexture==null");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();
        if(camera!=null) {
            camera.release();
            camera=null;
        }
        if(mCamera!=null) {
            mCamera.release();
            mCamera = null;
        }
    }


    private void releaseMediaRecorder() {
        if (recorder != null) {
            recorder.reset();   // clear recorder configuration
            recorder.release(); // release the recorder object
            recorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    private int getCameraDegress() {
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 90;
                break;
            case Surface.ROTATION_90:
                degrees = 0;
                break;
            case Surface.ROTATION_180:
                degrees = 270;
                break;
            case Surface.ROTATION_270:
                degrees = 180;
                break;
        }
        return degrees;
    }
}