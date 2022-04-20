package com.example.shopingprojecta.view.workwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.shopingprojecta.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class WorkPage extends AppCompatActivity {

    private static final String TAG = "WorkPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_page);
        processedTimeNow();

    }

    private void processedTimeNow (){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd/MMM/yyyy");
        String Time = simpleDateFormatTime.format(calendar.getTime());
        String Date = simpleDateFormatDate.format(calendar.getTime());

    }

}