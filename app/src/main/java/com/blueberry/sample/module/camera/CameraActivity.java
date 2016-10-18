package com.blueberry.sample.module.camera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/8/10.
 * <p/>
 * Camera 的简单使用 用来拍照和录像
 */
public class CameraActivity extends BaseActivity {

    private static final String TAG = "CameraActivity";

    @BindView(R.id.btn_take_picture)
    Button btnTakePicture;
    @BindView(R.id.btn_start_record)
    Button btnStartRecord;
    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    private SurfaceHolder holder;
    private Camera mCamera;

    private MediaRecorder mediaRecorder;
    private boolean started;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_camera);
        ButterKnife.bind(this);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initCamera() {
        /**
         * Step 1.
         * 获得一个Camera实例，（默认获得为后置摄像头)
         */
        if (mCamera == null) {
            mCamera = Camera.open();
        }

        /**
         * Step 2.
         * 获得默认的参数
         */
        Camera.Parameters parameters = mCamera.getParameters();

        // enable auto focus
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        if (parameters.getMaxNumMeteringAreas() > 0) { // check that metering areas are supported
            List<Camera.Area> meteringAreas = new ArrayList<>();
            Rect areaRect1 = new Rect(-100, -100, 100, 100);
            meteringAreas.add(new Camera.Area(areaRect1, 600));//set weight to 60%
            parameters.setMeteringAreas(meteringAreas);
        }




        parameters.setPictureFormat(ImageFormat.JPEG);

        /**
         * Stop 3.
         * 设置参数
         */
        mCamera.setParameters(parameters);

        /**
         * Step 4.
         * 修正当前的方向
         */
        setCameraDisplayOrientation(this, Camera.CameraInfo.CAMERA_FACING_BACK, mCamera);

        /**
         * Step 5.
         * 设置 SurfaceHolder
         */
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Step 6.
         * 开始预览
         */
        mCamera.startPreview();
//        mCamera.autoFocus(autoFocusCallback);
        if(parameters.getMaxNumDetectedFaces()>0){
            mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
                @Override
                public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                    Log.i(TAG, "检测到人脸: "+faces.length);
                    for (Camera.Face face:faces){
                        Log.i(TAG, "Face: "+face.score);
                    }
                }
            });
        }
        mCamera.stopFaceDetection();
        mCamera.startFaceDetection();
    }

    private Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                Log.i(TAG, "对焦成功");
            } else {
                Log.i(TAG, "对焦失败");
            }
        }
    };

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
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

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    private void initView() {
        holder = surfaceView.getHolder();
        holder.addCallback(callback);
        holder.setKeepScreenOn(true);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCamera == null) return false;
                int x = (int) event.getX();
                int y = (int) event.getY();
                int top = Math.max(y - 300, -1000);
                int bottom = Math.min(1000, y + 300);
                int right = Math.min(x + 300, 1000);
                int left = Math.max(-1000, x - 300);
                Rect rect = new Rect(left, top, right, bottom);
                Camera.Parameters parameters = mCamera.getParameters();
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    List<Camera.Area> areas = new ArrayList<Camera.Area>();
                    areas.add(new Camera.Area(rect, 1000));
                    parameters.setMeteringAreas(areas);
                    mCamera.setParameters(parameters);
                    mCamera.autoFocus(autoFocusCallback);
                }

                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        initCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
        /**
         * Step 9. 停止预览
         */
        mCamera.stopPreview();
        /**
         * Step 10. 释放
         */
        mCamera.release();
        mCamera = null;
    }

    @OnClick({R.id.btn_take_picture, R.id.btn_start_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_take_picture:
                /**
                 * Step 7.
                 * 拍照
                 */
                mCamera.takePicture(null, null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        if (data != null) {

                            /**
                             * Step 8. 重新开始预览
                             */
                            mCamera.startPreview();
                            writeImage2Sdcard(data);
                        }
                    }
                });
                break;
            case R.id.btn_start_record:
                if (!started) {
                    started = true;
                    btnStartRecord.setText("结束录像");
                    mediaRecorder = new MediaRecorder();
                    /**
                     * 录像 Step A.
                     */
                    mCamera.unlock();
                    /**
                     * 录像 Step B.
                     */
                    mediaRecorder.setCamera(mCamera);
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//                    mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                    mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory() +
                            File.separator + System.currentTimeMillis() + ".3gp");
                    try {
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.start();
//
                } else {
                    started = false;
                    btnStartRecord.setText("开始录像");
                    mediaRecorder.stop();
                    mediaRecorder.reset();
                    mediaRecorder.release();
                    /**
                     * 录像 Step C.
                     */
                    try {
                        mCamera.reconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mCamera.lock();

                    /**
                     * 录像 Step D.
                     */
                    mCamera.startPreview();
                }


                break;
        }
    }


    private void writeImage2Sdcard(final byte[] data) {
        new Thread() {
            @Override
            public void run() {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(new File("sdcard/test",
                            System.currentTimeMillis() + ".jpg"));
                    out.write(data);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            initCamera();
            Log.i(TAG, "surfaceCreated: ");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.i(TAG, "surfaceChanged: ");
            setCameraDisplayOrientation(CameraActivity.this,
                    Camera.CameraInfo.CAMERA_FACING_BACK, mCamera);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.i(TAG, "surfaceDestroyed: ");
        }
    };

}
