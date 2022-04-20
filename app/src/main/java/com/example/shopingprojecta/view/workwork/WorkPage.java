package com.example.shopingprojecta.view.workwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityWorkPageBinding;
import com.example.shopingprojecta.tagcast.AppInfo;
import com.example.shopingprojecta.tagcast.ErrorDialogFragment;
import com.example.shopingprojecta.tagcast.ErrorDialogWorkPage;
import com.example.shopingprojecta.view.CheckinPage;
import com.example.shopingprojecta.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.tagcast.bleservice.TGCErrorCode;
import jp.tagcast.bleservice.TGCScanListener;
import jp.tagcast.bleservice.TGCState;
import jp.tagcast.bleservice.TGCType;
import jp.tagcast.bleservice.TagCast;
import jp.tagcast.helper.TGCAdapter;

public class WorkPage extends AppCompatActivity {

    public TGCAdapter tgcAdapterWkPage;
    ActivityWorkPageBinding workPageBinding;

    FirebaseAuth firebaseAuthWorkPage;


    private boolean flgBeaconWkPage = false;
    private CountDownTimer countDownTimer;

    private SoundPool soundPool;
    private int soundIdButton;
    private int soundIdCheckIn;
    private int soundIdStampDisplay;
    private int soundIdStampReduction;
    private int soundIdSignal;
    private String TCentityNumber, TCid, longmap,latmap, serial;
    private Map<String,String> map;

    public int mErrorDialogType = ErrorDialogFragment.TYPE_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workPageBinding = ActivityWorkPageBinding.inflate(getLayoutInflater());
        setContentView(workPageBinding.getRoot());
        //setContentView(R.layout.activity_work_page);


        final Context context = getApplicationContext();
        tgcAdapterWkPage=TGCAdapter.getInstance(context);

        TagcastAA();
        pressButton();

    }

    ////////////////////////////////////
      //ctc
    ///////////////////////////////////
    private void pressButton(){

        workPageBinding.buttonStarWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkPage.this," Dang Check ",Toast.LENGTH_SHORT).show();
                tgcAdapterWkPage.setScanInterval(10000);
                tgcAdapterWkPage.startScan();

                Animation animation = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_a);
                Animation animation1 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_b);
                workPageBinding.lottieScanStar.setVisibility(View.VISIBLE);
                workPageBinding.lottieScanStar.startAnimation(animation);

                //getUserLogin();

                FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();

                if (userID != null) {
                    for (UserInfo profile : userID.getProviderData()) {
                        // Id of the provider (ex: google.com)
                        String providerId = profile.getProviderId();
                        // UID specific to the provider
                        String uid = profile.getUid();
                        // Name, email address, and profile photo Url
                        String name = profile.getDisplayName();
                        String email = profile.getEmail();
                        String emaill = userID.getEmail();

                    }
                }

                countDownTimer = new CountDownTimer(10500,1000) {
                    @Override
                    public void onTick(long l) {


                    }

                    @Override
                    public void onFinish() {
                        if (flgBeaconWkPage){
                            processedTimeNow();
                        }else{
                            Toast.makeText(WorkPage.this,"CheckLai",Toast.LENGTH_SHORT).show();
                        }
                        workPageBinding.lottieScanStar.startAnimation(animation1);
                        workPageBinding.lottieScanStar.setVisibility(View.GONE);

                    }
                };countDownTimer.start();

            }
        });

    }

//    private  void getUserLogin(){
//
//        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (userID != null) {
//            for (UserInfo profile : userID.getProviderData()) {
//                // Id of the provider (ex: google.com)
//                String providerId = profile.getProviderId();
//                // UID specific to the provider
//                String uid = profile.getUid();
//                // Name, email address, and profile photo Url
//                String name = profile.getDisplayName();
//                String email = profile.getEmail();
//
//            }
//        }
//
//    }

    private void TagcastAA(){

        final TGCScanListener mTGCScanListener = new TGCScanListener() {
            @Override
            public void changeState(TGCState tgcState) {

            }

            @Override
            public void didDiscoverdTagcast(TagCast tagCast) {

                if(tagCast.getTgcType() == TGCType.TGCTypePaperBeacon){
                    flgBeaconWkPage = true;}
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
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_RETRY;
                        title = getString(R.string.unknownErrorTitle);
                        message = getString(R.string.unknownErrorMessage);
                        break;
                    case TGCErrorCodeDatabase:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_RETRY;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeNetwork:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_RETRY;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.networkErrorMessage);
                        break;
                    case TGCErrorCodeBluetooth:
                        if (mErrorDialogType == ErrorDialogWorkPage.TYPE_RETRY) {
                            return;
                        }
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_RETRY;
                        title = getString(R.string.bluetoothErrorTitle);
                        message = getString(R.string.bluetoothErrorMessage);
                        break;
                    case TGCErrorCodeDebugDataInvalid:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_OK;
                        title = getString(R.string.databaseErrorTitle);
                        message = getString(R.string.databaseErrorMessage);
                        break;
                    case TGCErrorCodeAPIKeyNotRegistered:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_OK;
                        title = getString(R.string.apiKeyNotRegisteredErrorTitle);
                        message = getString(R.string.apiKeyNotRegisteredErrorMessage);
                        break;
                    case TGCErrorCodeInvalidScanInterval:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_NO;
                        break;
                    case TGCErrorCodePermissionDenied:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_OK;
                        title = getString(R.string.permissionDeniedErrorTitle);
                        message = getString(R.string.permissionDeniedErrorMessage);
                        break;
                    case TGCErrorCodeMasterDataFailedUpdate:
                        mErrorDialogType = ErrorDialogWorkPage.TYPE_UPDATE;
                        title = getString(R.string.networkErrorTitle);
                        message = getString(R.string.failedUpdateErrorMessage);
                        break;
                    case TGCErrorCodeLocationAccess:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mErrorDialogType == ErrorDialogWorkPage.TYPE_RETRY) {
                                return;
                            }
                            mErrorDialogType = ErrorDialogWorkPage.TYPE_RETRY;
                            title = getString(R.string.localeAccessErrorTitle);
                            message = getString(R.string.localeAccessErrorMessage);
                        } else {
                            return;
                        }
                        break;
                    default:
                        break;
                }
                if (mErrorDialogType != ErrorDialogWorkPage.TYPE_NO) {
                    ErrorDialogWorkPage errorDialog = new ErrorDialogWorkPage();
                    Bundle arguments = new Bundle();
                    arguments.putString(ErrorDialogWorkPage.KEY_TITLE, title);
                    arguments.putString(ErrorDialogWorkPage.KEY_MESSAGE, message);
                    arguments.putInt(ErrorDialogWorkPage.KEY_TYPE, mErrorDialogType);
                    errorDialog.setArguments(arguments);
                    ErrorDialogWorkPage.showDialogFragmentWorkPage(fragmentManager, ErrorDialogWorkPage.class.getName(), errorDialog);
                }

            }
        };

        tgcAdapterWkPage.setTGCScanListener(mTGCScanListener);

    }

    private void processedTimeNow (){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd/MMM/yyyy");
        String Time = simpleDateFormatTime.format(calendar.getTime());
        String Date = simpleDateFormatDate.format(calendar.getTime());

    }
    //////////////////////////////////
        //    hdb
    //////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();

        flgBeaconWkPage=false;
        if(checkPermission()){
            tgcAdapterWkPage.prepare();
        }
        Log.d("here","resu");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tgcAdapterWkPage.stopScan();
        Log.d("here","Pau");

    }

    private boolean checkPermission() {
        List<String> permissions = ((AppInfo) getApplication()).checkPermission();
        if (permissions.size() == 0) {
            return true;
        } else {
            try {
                String[] array = new String[permissions.size()];
                permissions.toArray(array);
                ActivityCompat.requestPermissions(WorkPage.this, array, 1);
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
                tgcAdapterWkPage.prepare();
                // Bắt đầu quét
                tgcAdapterWkPage.setScanInterval(10000);
                tgcAdapterWkPage.startScan();
            } else {
                finish();
            }
        }

    }
}