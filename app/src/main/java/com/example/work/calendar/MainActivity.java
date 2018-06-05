package com.example.work.calendar;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText mDateEditText;
    Calendar mCurrentDate;
    Bitmap mGeneratedDateIcon;
    ImageGenerator mImageGenerator;
    ImageView mDisplayGenerateImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageGenerator = new ImageGenerator(this);
        mDateEditText = (EditText)findViewById(R.id.txtDateEntered);
        mDisplayGenerateImage = (ImageView)findViewById(R.id.imageGen);

        mImageGenerator.setIconSize(50,50);
        mImageGenerator.setDateSize(30);
        mImageGenerator.setMonthSize(10);

        mImageGenerator.setDatePosition(42);
        mImageGenerator.setMonthPosition(14);

        mImageGenerator.setDateColor(Color.parseColor("#3c6eaf"));
        mImageGenerator.setMonthColor(Color.WHITE);

        mImageGenerator.setStorageToSDCard(true);

        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentDate = Calendar.getInstance();
                int year = mCurrentDate.get(Calendar.YEAR);
                int month = mCurrentDate.get (Calendar.MONTH);
                int day = mCurrentDate.get (Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selecteDay) {
                        mDateEditText.setText(selecteDay+"-"+selectedMonth+"-"+selectedYear);

                        mCurrentDate.set(selectedYear, selectedMonth, selecteDay);
                        mGeneratedDateIcon = mImageGenerator.generateDateImage(mCurrentDate,R.drawable.empty_calendar);
                        mDisplayGenerateImage.setImageBitmap(mGeneratedDateIcon);


                    }

                } , year, month, day);
                mDatePicker.show();
            }
        });
    }
}
