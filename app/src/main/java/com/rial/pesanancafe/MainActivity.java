package com.rial.pesanancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // Date
    private DatePicker datePicker;
    private Calendar calendarDate;
    private int year, month, day;
    private TextView dateView;

    // Time
    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendarTime;
    private String format = "";
    private int hour, minute;

    // Button Daftar Menu
    Button btnmenu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Date
        dateView = (TextView) findViewById(R.id.ViewDate);
        calendarDate = Calendar.getInstance();
        year = calendarDate.get(Calendar.YEAR);

        month = calendarDate.get(Calendar.MONTH);
        day = calendarDate.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        // Time
        time = (TextView) findViewById(R.id.ViewTime);
        calendarTime = Calendar.getInstance();
        hour = calendarTime.get(Calendar.HOUR_OF_DAY);
        minute = calendarTime.get(Calendar.MINUTE);
        showTime(hour, minute);

        // Daftar Menu
        btnmenu = (Button) findViewById(R.id.btnDaftarMenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabelActivity.class);
                startActivity(intent);
            }
        });

    }
    // Date
    public void setDate(View view) {
        showDialog(999);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    // Time
    public void setTime(View view) {
        showDialog(888);
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            showTime(hourOfDay, minute);
        }
    };

    private void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        } else if (id == 888) {
            return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }
}