package com.example.shopingprojecta.view.workwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.fragment.app.FragmentManager;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityWorkPageBinding;
import com.example.shopingprojecta.login.IntroPage;
import com.example.shopingprojecta.login.LoginPage;
import com.example.shopingprojecta.models.WorkerUser;
import com.example.shopingprojecta.tagcast.AppInfo;
import com.example.shopingprojecta.tagcast.ErrorDialogFragment;
import com.example.shopingprojecta.tagcast.ErrorDialogWorkPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jp.tagcast.bleservice.TGCErrorCode;
import jp.tagcast.bleservice.TGCScanListener;
import jp.tagcast.bleservice.TGCState;
import jp.tagcast.bleservice.TGCType;
import jp.tagcast.bleservice.TagCast;
import jp.tagcast.helper.TGCAdapter;



public class WorkPage extends AppCompatActivity {

    ActivityWorkPageBinding workPageBinding;
    //////////////Tagcast////////////////
    public TGCAdapter tgcAdapterWkPage;
    private boolean flgBeaconWkPage = false;
    public int mErrorDialogType = ErrorDialogFragment.TYPE_NO;

    private static final String TAG = "WorkPage";
    private WorkerUser workerUser;


    //////////////FireBase///////////////
    FirebaseAuth firebaseAuthWorkPage;
    DatabaseReference workPageDatabase;
    FirebaseFirestore firebaseFirestoreWorKPage;
    private FirebaseUser userID;
    // Id of the provider (ex: google.com)
    private String providerId;
    // UID specific to the provider
    private String uid;
    // Name, email address, and profile photo Url
    private String name;
    private String email;
    private String emaill;
    //////////////Opject Time/////////////////
    private Calendar calendar,calendarEnd;
    private SimpleDateFormat simpleDateFormatTime,simpleDateFormatTime1,simpleDateFormatfullS;
    private SimpleDateFormat simpleDateFormatDate,simpleDateFormatDate1,simpleDateFormatfullE;
    private String Time,TimeE,TimeEd;
    private String date;
    private Date days;
    private Date daye;
    private DateFormat dateFormatStar,dateFormatEnd,dateFormatFull;
    private CountDownTimer countDownTimerStar,countDownTimerEnd;
    /////////////Sound//////////////////
    private SoundPool soundPool;
    private int soundIdButton;
    private int soundIdCheckIn;
    private int soundIdStampDisplay;
    private int soundIdStampReduction;
    private int soundIdSignal;
    private String TCentityNumber, TCid, longmap,latmap, serial;
    private Map<String,String> map;
    ////////////////////////////
    private int btnUpdate,checkdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workPageBinding = ActivityWorkPageBinding.inflate(getLayoutInflater());
        setContentView(workPageBinding.getRoot());
        //setContentView(R.layout.activity_work_page);

        workerUser = new WorkerUser();
        final Context context = getApplicationContext();
        tgcAdapterWkPage=TGCAdapter.getInstance(context);
        btnUpdate =0;
        checkdata =0;
        getUIandDay();
        xlybd();
        readFirebaseDatabase();

        //pressButtonUpdate();
        TagcastAA();
        pressButton();


    }
    /***********************************
      ///      person program     ///
    ***********************************/

    /***** Firebase handling *****/

    private void FirebaseDatabaseCloudStart(){

        firebaseFirestoreWorKPage = FirebaseFirestore.getInstance();

        firebaseFirestoreWorKPage.collection(userID.getEmail())
                .document("Work").collection(workerUser.getDayWork())
                .document("WorkTime")
                .set(workerUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    private void FirebaseDatabaseCloudEnd(){

        firebaseFirestoreWorKPage = FirebaseFirestore.getInstance();

        firebaseFirestoreWorKPage.collection(userID.getEmail())
                .document("Work").collection(workerUser.getDayWork())
                .document("WorkTime")
                .set(workerUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    private void readFirebaseDatabase(){

        firebaseFirestoreWorKPage = FirebaseFirestore.getInstance();

        firebaseFirestoreWorKPage.collection(emaill).document("Work").collection(workerUser.getDayWork())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               Map data2 = document.getData();
                               String name1 = (String) data2.get("nameWorker");
                               String name2 = (String) data2.get("dayWork");
                               String name3 = (String) data2.get("timeStarWork") ;
                               String name4 = (String) data2.get("timeWork");
                               String name5 = (String) data2.get("totalTime");
                               Toast.makeText(WorkPage.this, name1 + "\n" + name2+"\n"+name3 + "\n" + name4, Toast.LENGTH_SHORT).show();

                               workPageBinding.txtNameUser.setText(name1);
                               workPageBinding.txtDayWork.setText(name2);
                               workPageBinding.txtTimeStarWork.setText(name3);
                               workPageBinding.txtTimeWork.setText(name4);
                               workPageBinding.txtEtime.setText(name5);

                               workerUser.setDayWork((String) workPageBinding.txtDayWork.getText());
                               workerUser.setNameWorker((String) workPageBinding.txtNameUser.getText());
                               workerUser.setTimeWork((String) workPageBinding.txtTimeWork.getText());
                               workerUser.setTimeStarWork((String) workPageBinding.txtTimeStarWork.getText());
                               workerUser.setTotalTime((String) workPageBinding.txtEtime.getText());

                            }

                        }else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(WorkPage.this,"Error Update data",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
        checkdata=1;
        preshandingFirsh();

    }

    /***** time handling *****/

    private void getUIandDay(){

        getUserLogin();
        workPageBinding.txtNameUser.setText(emaill);
        calendar = Calendar.getInstance();
        simpleDateFormatDate = new SimpleDateFormat("dd-MMM-yyyy");
        date = simpleDateFormatDate.format(calendar.getTime());
        workPageBinding.txtDayWork.setText(date);
        workerUser.setNameWorker(emaill);
        workerUser.setDayWork(date);

    }

    private void processedTimeNow (){

        calendar = Calendar.getInstance();

        simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormatDate = new SimpleDateFormat("dd-MMM-yyyy");
        date = simpleDateFormatDate.format(calendar.getTime());
        dateFormatStar = simpleDateFormatTime;
        days = dateFormatStar.getCalendar().getTime(); // time start
        Time = simpleDateFormatTime.format(calendar.getTime());

    }

    private void processedTimeNowEnd(){

        calendarEnd = Calendar.getInstance();
        simpleDateFormatTime1 = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormatDate1 = new SimpleDateFormat("dd-MMM-yyyy");
        date = simpleDateFormatDate.format(calendarEnd.getTime());
        dateFormatEnd = simpleDateFormatTime1;
        daye = dateFormatEnd.getCalendar().getTime();
        TimeEd = simpleDateFormatTime1.format(calendarEnd.getTime());
        converTime();

//        long dift = daye.getTime() - days.getTime();
//        long hours = TimeUnit.MILLISECONDS.toHours(dift);
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(dift) % 60;
//        long seconds = TimeUnit.MILLISECONDS.toSeconds(dift) % 60;
//        long milliseconds = dift % 1000;
//        TimeE = String.format("%02d:%02d:%02d,%02d", hours, minutes, seconds, milliseconds);
    }

    private void converTime(){

        java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
        Date dateA = null;
        try {
            dateA = df.parse((String) workPageBinding.txtTimeStarWork.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateB = null;
        try {
            dateB = df.parse(TimeEd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = dateB.getTime() - dateA.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
        long milliseconds = diff % 1000;
        TimeE = String.format("%02d:%02d:%02d,%02d", hours, minutes, seconds, milliseconds);

    }

    /***** SetText handling *****/

    private void setTXTStart(){

        workPageBinding.txtTimeStarWork.setText(Time);
        workerUser.setTimeStarWork(Time);

    }

    private void setTXTEnd(){

        workPageBinding.txtTimeWork.setText(TimeEd);
        workerUser.setTimeWork(TimeEd);
        workPageBinding.txtEtime.setText(TimeE);
        workerUser.setTotalTime(TimeE);


    }

    /***** pressButton handling *****/

    private void preshandingFirsh(){

        if (checkdata==1){

                    if (!TextUtils.isEmpty(workPageBinding.txtTimeStarWork.getText().toString())){

                        workPageBinding.buttonEndWord.setEnabled(true);
                        workPageBinding.buttonEndWord.setVisibility(View.VISIBLE);
                        workPageBinding.buttonStarWord.setEnabled(false);
                        workPageBinding.buttonStarWord.setVisibility(View.GONE);

                    }else{

                        workPageBinding.buttonStarWord.setEnabled(true);
                        workPageBinding.buttonStarWord.setVisibility(View.VISIBLE);
                        workPageBinding.buttonEndWord.setEnabled(false);
                        workPageBinding.buttonEndWord.setVisibility(View.GONE);

                    }

        }

    }

    private void pressButton(){

        pressButtonStart();
        pressButtonEnd();

    }

    private void pressButtonStart(){



        workPageBinding.buttonStarWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(workPageBinding.txtTimeStarWork.getText())){
                    Toast.makeText(WorkPage.this," You had Checkin Start ",Toast.LENGTH_SHORT).show();
                    preshandingFirsh();
                }else {

                    Toast.makeText(WorkPage.this," Checking Start ",Toast.LENGTH_SHORT).show();

                    tgcAdapterWkPage.setScanInterval(10000);
                    tgcAdapterWkPage.startScan();

                    Animation animation = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_a);
                    Animation animation1 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_b);

                    workPageBinding.lottieScanStar.setVisibility(View.VISIBLE);
                    workPageBinding.lottieScanStar.startAnimation(animation);
                    disableButtonStartEnd();

                    countDownTimerStar = new CountDownTimer(10500,1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            if (flgBeaconWkPage){
                                Toast.makeText(WorkPage.this," Start Check Success ",Toast.LENGTH_SHORT).show();
                                processedTimeNow();
                                setTXTStart();
                                FirebaseDatabaseCloudStart();
                                preshandingFirsh();
                                //preshandingSecon();

                            }else{
                                Toast.makeText(WorkPage.this," Start Check Fail",Toast.LENGTH_SHORT).show();
                            }

                            workPageBinding.lottieScanStar.startAnimation(animation1);
                            workPageBinding.lottieScanStar.setVisibility(View.GONE);
                            enableButtonStartEnd();

                        }
                    };countDownTimerStar.start();
                }
            }
        });

    }

    private void pressButtonEnd(){
        workPageBinding.buttonEndWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(workPageBinding.txtTimeWork.getText())){
                    Toast.makeText(WorkPage.this," You had Checkin End ",Toast.LENGTH_SHORT).show();
                    preshandingFirsh();
                }else{

                    Toast.makeText(WorkPage.this," Checking End ",Toast.LENGTH_SHORT).show();
                    tgcAdapterWkPage.setScanInterval(10000);
                    tgcAdapterWkPage.startScan();

                    Animation animation2 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_a);
                    Animation animation3 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_b);

                    workPageBinding.lottieScanEnd.setVisibility(View.VISIBLE);
                    workPageBinding.lottieScanEnd.startAnimation(animation2);
                    disableButtonStartEnd();

                    countDownTimerEnd = new CountDownTimer(10500,1000) {
                        @Override
                        public void onTick(long l) {

                        }
                        @Override
                        public void onFinish() {
                            if (flgBeaconWkPage){
                                Toast.makeText(WorkPage.this," End Check Success ",Toast.LENGTH_SHORT).show();
                                processedTimeNowEnd();
                                setTXTEnd();
                                FirebaseDatabaseCloudEnd();

                            }else{
                                Toast.makeText(WorkPage.this," End Check Fail ",Toast.LENGTH_SHORT).show();
                            }

                            workPageBinding.lottieScanEnd.startAnimation(animation3);
                            workPageBinding.lottieScanEnd.setVisibility(View.GONE);
                            enableButtonStartEnd();
                        }
                    };countDownTimerEnd.start();
                }
            }
        });
    }

    private void xlybd(){

            workPageBinding.buttonStarWord.setEnabled(false);
            workPageBinding.buttonStarWord.setVisibility(View.GONE);
            workPageBinding.buttonEndWord.setEnabled(false);
            workPageBinding.buttonEndWord.setVisibility(View.GONE);

    }

    private void pressButtonUpdate(){



    }

    private void enableButtonStartEnd(){
        Animation animation5 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apha_animation_enable_type_a);
        workPageBinding.SSSSSSStarCart.startAnimation(animation5);
        workPageBinding.EEEEEECart.startAnimation(animation5);

        workPageBinding.buttonStarWord.setEnabled(true);
        workPageBinding.buttonEndWord.setEnabled(true);

    }

    private void disableButtonStartEnd(){

        Animation animation6 = AnimationUtils.loadAnimation(WorkPage.this,R.anim.apla_animation_disable_type_a);
        workPageBinding.SSSSSSStarCart.startAnimation(animation6);
        workPageBinding.EEEEEECart.startAnimation(animation6);

        workPageBinding.buttonStarWord.setEnabled(false);
        workPageBinding.buttonEndWord.setEnabled(false);

    }

    /***** getUserLogin handling *****/

    private  void getUserLogin(){

        userID = firebaseAuthWorkPage.getInstance().getCurrentUser();

        if (userID != null) {
            for (UserInfo profile : userID.getProviderData()) {
                // Id of the provider (ex: google.com)
                providerId = profile.getProviderId();
                // UID specific to the provider
                uid = profile.getUid();
                // Name, email address, and profile photo Url
                name = profile.getDisplayName();
                email = profile.getEmail();
                emaill = userID.getEmail(); // read email user now

            }
        }
    }

    /***** Tagcast handling *****/

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

    /**********************************
        //    hdb
    **********************************/

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