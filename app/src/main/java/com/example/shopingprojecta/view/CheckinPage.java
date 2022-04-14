package com.example.shopingprojecta.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.tagcast.AppInfo;
import com.example.shopingprojecta.tagcast.ErrorDialogFragment;

import java.util.List;
import java.util.Map;

import jp.tagcast.bleservice.TGCErrorCode;
import jp.tagcast.bleservice.TGCScanListener;
import jp.tagcast.bleservice.TGCState;
import jp.tagcast.bleservice.TGCType;
import jp.tagcast.bleservice.TagCast;
import jp.tagcast.helper.TGCAdapter;

public class CheckinPage extends AppCompatActivity {

    public TGCAdapter tgcAdapter;

    private boolean flgBeacon = false;

    private SoundPool soundPool;
    private int soundIdButton;
    private int soundIdCheckIn;
    private int soundIdStampDisplay;
    private int soundIdStampReduction;
    private int soundIdSignal;
    private String TCentityNumber, TCid, longmap,latmap, serial;
    private Map<String,String> map;

    public int mErrorDialogType = ErrorDialogFragment.TYPE_NO;

    Button btkScan;
    ProgressBar progressBar;
    int valuePGB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_page);
        final Context context = getApplicationContext();
        tgcAdapter= TGCAdapter.getInstance(context);

        btkScan = findViewById(R.id.buttonScan);
        progressBar = findViewById(R.id.progressBarLoading);

        btkScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tgcAdapter.setScanInterval(10000);
                tgcAdapter.startScan();

                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long l) {
                        valuePGB = progressBar.getProgress();
                        if(valuePGB>=progressBar.getMax()){valuePGB=0;}
                        progressBar.setProgress(valuePGB + 10);
                    }

                    @Override
                    public void onFinish() {
                        if (flgBeacon){
                            Intent intent = new Intent(CheckinPage.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(CheckinPage.this,"Thanh Cong",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CheckinPage.this,"CheckLai",Toast.LENGTH_SHORT).show();
                        }

                    }
                };countDownTimer.start();


            }
        });

        final TGCScanListener mTGCScanListener = new TGCScanListener() {
            @Override
            public void changeState(TGCState tgcState) {

            }

            @Override
            public void didDiscoverdTagcast(TagCast tagCast) {
                if(tagCast.getTgcType() == TGCType.TGCTypePaperBeacon){
                    flgBeacon = true;}
                Map<String, String> s = tagCast.getDetails();
                    Log.d("here",s.toString());

            }

            @Override
            public void didScannedTagcasts(List<TagCast> list) {

            }

            @Override
            public void didScannedStrengthOrderTagcasts(List<TagCast> list) {

            }

            @Override
            public void didFailWithError(TGCErrorCode tgcErrorCode) {

                final FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager == null) {
                    return;
                }
                // Vì nó có thể được gọi lại trong khoảng thời gian ngắn
                // Cần thực hiện các nội dung cần xử lý ở đây sau khi đã chú ý đầy đủ.
                // Ngoài ra, khi thiết lập một worker thread, cần phải thực hiện nó để các luồng không bị phân tán.
                String title = null;
                String message = null;
                switch (tgcErrorCode) {
                    case TGCErrorCodeUnknown:
                        mErrorDialogType = ErrorDialogFragment.TYPE_RETRY;
                        title = getString(R.string.unknownErrorTitle);
                        message = getString(R.string.unknownErrorMessage);
                        break;
                    case TGCErrorCodeDatabase:
                        mErrorDialogType = ErrorDialogFragment.TYPE_RETRY;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeNetwork:
                        mErrorDialogType = ErrorDialogFragment.TYPE_RETRY;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.networkErrorMessage);
                        break;
                    case TGCErrorCodeBluetooth:
                        if (mErrorDialogType == ErrorDialogFragment.TYPE_RETRY) {
                            return;
                        }
                        mErrorDialogType = ErrorDialogFragment.TYPE_RETRY;
                        title = getString(R.string.bluetoothErrorTitle);
                        message = getString(R.string.bluetoothErrorMessage);
                        break;
                    case TGCErrorCodeDebugDataInvalid:
                        mErrorDialogType = ErrorDialogFragment.TYPE_OK;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeAPIKeyNotRegistered:
                        mErrorDialogType = ErrorDialogFragment.TYPE_OK;
                        title = getString(R.string.apiKeyNotRegisteredErrorTitle);
                        message = getString(R.string.apiKeyNotRegisteredErrorMessage);
                        break;
                    case TGCErrorCodeInvalidScanInterval:
                        mErrorDialogType = ErrorDialogFragment.TYPE_NO;
                        break;
                    case TGCErrorCodePermissionDenied:
                        mErrorDialogType = ErrorDialogFragment.TYPE_OK;
                        title = getString(R.string.permissionDeniedErrorTitle);
                        message = getString(R.string.permissionDeniedErrorMessage);
                        break;
                    case TGCErrorCodeMasterDataFailedUpdate:
                        mErrorDialogType = ErrorDialogFragment.TYPE_UPDATE;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.failedUpdateErrorMessage);
                        break;
                    case TGCErrorCodeLocationAccess:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mErrorDialogType == ErrorDialogFragment.TYPE_RETRY) {
                                return;
                            }
                            mErrorDialogType = ErrorDialogFragment.TYPE_RETRY;
                            title = getString(R.string.localeAccessErrorTitle);
                            message = getString(R.string.localeAccessErrorMessage);
                        } else {
                            return;
                        }
                        break;
                    default:
                        break;
                }
                if (mErrorDialogType != ErrorDialogFragment.TYPE_NO) {
                    ErrorDialogFragment errorDialog = new ErrorDialogFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString(ErrorDialogFragment.KEY_TITLE, title);
                    arguments.putString(ErrorDialogFragment.KEY_MESSAGE, message);
                    arguments.putInt(ErrorDialogFragment.KEY_TYPE, mErrorDialogType);
                    errorDialog.setArguments(arguments);
                    ErrorDialogFragment.showDialogFragment(fragmentManager, ErrorDialogFragment.class.getName(), errorDialog);
                }
            }
        };
        tgcAdapter.setTGCScanListener(mTGCScanListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        flgBeacon=false;
        if(checkPermission()){
            tgcAdapter.prepare();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        tgcAdapter.stopScan();
    }

    /**
     * Yêu cầu sự cho phép
     */
    private boolean checkPermission() {
        List<String> permissions = ((AppInfo) getApplication()).checkPermission();
        if (permissions.size() == 0) {
            return true;
        } else {
            try {
                String[] array = new String[permissions.size()];
                permissions.toArray(array);
                ActivityCompat.requestPermissions(CheckinPage.this, array, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions.length == 0 || grantResults.length == 0) {
                return;
            }
            boolean isGranted = true;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults.length <= i || grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                }
            }
            if (isGranted) {
                // Thu thập dữ liệu quản lý TAGCAST
                tgcAdapter.prepare();
                // Bắt đầu quét
                tgcAdapter.setScanInterval(10000);
                tgcAdapter.startScan();
            } else {
                finish();
            }
        }
    }
}