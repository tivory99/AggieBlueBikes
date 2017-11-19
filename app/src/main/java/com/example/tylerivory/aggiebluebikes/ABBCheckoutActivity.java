package com.example.tylerivory.aggiebluebikes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by crlsk on 11/17/2017.
 */

public class ABBCheckoutActivity extends AppCompatActivity {

    private TextView clockText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abb_checkout);
    }

    public void setClocTextView(TextView clocktv)
    {
        this.clockText = clocktv;
    }
    public void SaveCheckout(View view) {
        Calendar beginTime = Calendar.getInstance();
        DatePicker picker = findViewById(R.id.checkout_date);
        beginTime.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth(),9,30);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=2;COUNT=7")
                .putExtra(CalendarContract.Events.TITLE, "Aggie Blue Bik" +
                        "es: 2 week checkout")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Remember to bring in the bike for the 2 week checkout")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Aggie Blue Bikes Shop")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE)
                .putExtra(Intent.EXTRA_EMAIL, "aggiebluebikesagreements@gmail.com");
        Date duedate = new Date(beginTime.getTimeInMillis() + (3 * TimeConstants.THIRY_DAYS));
        writeToFile(duedate.getTime()+"", getBaseContext());


        //this.clockText.setText(duedate.toString().substring(0,duedate.toString().length()-9));//


        startActivity(intent);
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("duedate.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
