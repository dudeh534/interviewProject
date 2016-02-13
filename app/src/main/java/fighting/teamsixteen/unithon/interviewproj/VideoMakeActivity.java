package fighting.teamsixteen.unithon.interviewproj;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class VideoMakeActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    // 아웃풋 파일 경로
    private static final String OUTPUT_FILE = "/sdcard/videooutput.mp4";
    // 녹화 시간 - 10초
    private static final int RECORDING_TIME = 120000;

    int currentCameraId = 1;
    TextureView mTexture;
    //    RelativeLayout cameraLayout;
    private Camera mCamera;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_make);
        mTexture = (TextureView) findViewById(R.id.textureView1);
        mTexture.setSurfaceTextureListener(this);
//        setContentView(mTexture);
//        cameraLayout = (RelativeLayout) findViewById(R.id.cameraLayout);
////        cameraLayout.addView(mTexture);
        Button btn = (Button) findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(VideoMakeActivity.this, MainActivity.class);
//                startActivity(i);
            }
        });
        final ImageView frontback = (ImageView) findViewById(R.id.frontback);
        frontback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCamera();
//                if (currentCameraId == 0)
//                    currentCameraId = 1;
//                else if (currentCameraId == 1)
//                    currentCameraId = 0;
//                Log.e("currentCameraId", currentCameraId + "");
//                mCamera.stopPreview();
//                mCamera.release();
//                setCameraPreview(mTexture, currentCameraId);
            }
        });
        ImageView imgview = (ImageView) findViewById(R.id.btn_start);
        imgview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.e("CAM TEST", "REC START!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                if (mTexture == null) {
                    Log.e("CAM TEST", "View Err!!!!!!!!!!!!!!!");
                }
                beginRecording(mTexture);

            }
        });
        ImageView recStop = (ImageView) findViewById(R.id.btn_stop);
        recStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 레코더 객체가 존재할 경우 이를 스톱시킨다

                if (recorder != null) {
                    Log.e("CAM TEST", "CAMERA STOP!!!!!");
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }
                // 프리뷰가 없을 경우 다시 가동 시킨다
                if (mCamera == null) {
                    Log.e("CAM TEST", "Preview Restart!!!!!");
                    // 프리뷰 다시 설정{
//                mCamera.stopPreview();
//                mCamera.release();
                    setCameraPreview(mTexture, currentCameraId);
                    // 프리뷰 재시작

//                    mCamera.startPreview();
                }
            }
        });
    }

    private void startCamera(int facing) {
        mCamera = Camera.open(facing);
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        mTexture.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));

        try {
            mCamera.setPreviewTexture(mTexture.getSurfaceTexture());
        } catch (IOException t) {
        }
        mCamera.startPreview();
//        mTexture.setAlpha(1.0f);
//        mTexture.setRotation(getCameraDegress());//90.0f);//
        //////////////////////
//        mCamera = openCamera(facing);
////        if (mCamera == null) {
////            mCamera = Camera.open();
////            currentCameraId = 0;
////        }
//        try {
//            mCamera.setPreviewTexture();
//        } catch (IOException e){
//            mCamera.release();
//            mCamera = null;
//            e.printStackTrace();
//        }
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

        if (currentCameraId == 0) {
            currentCameraId = 1;
            startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else {
            currentCameraId = 0;
            startCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
        }
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1, int arg2) {
        mCamera = Camera.open(1);
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        mTexture.setLayoutParams(new FrameLayout.LayoutParams(
                previewSize.width, previewSize.height, Gravity.CENTER));

        try {
            mCamera.setPreviewTexture(arg0);
        } catch (IOException t) {
        }
        mCamera.startPreview();
        mTexture.setAlpha(1.0f);
        mTexture.setRotation(getCameraDegress());//90.0f);//
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
        // 파일 생성/초기화
        Log.e("CAM TEST", "#2 Create File!!!");
        File outFile = new File(OUTPUT_FILE);
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

            Camera camera = Camera.open(currentCameraId);
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
                recorder.setOrientationHint(getCameraDegress());//90);
            } else {
                recorder.setVideoSize(320, 240);//640, 480);
                recorder.setVideoFrameRate(5);
                recorder.setVideoEncodingBitRate(1 * 1000 * 1000);
                recorder.setOrientationHint(getCameraDegress() + 180);//90);
            }
            // Video/Audio 인코더 설정
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // 프리뷰를 보여줄 서피스 설정
            recorder.setPreviewDisplay(new Surface(textureView.getSurfaceTexture()));

            // 녹화할 대상 파일 설정
            recorder.setOutputFile(OUTPUT_FILE);
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
//        try {
//            if(mTexture!=null){
//                setCameraPreview(mTexture,currentCameraId);
////                mTexture.setSurfaceTextureListener(this);
//            }else{
//                Log.e("onResume","mTexture==null");
//            }
//
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
        releaseCamera();
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
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    private int getCameraDegress() {
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result = (90 - degrees + 360) % 360;
        return result;
    }
}