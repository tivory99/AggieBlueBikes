package com.example.tylerivory.aggiebluebikes;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by crlsk on 11/17/2017.
 */

public class ABBCheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abb_checkout);
    }

    public void SaveCheckout(View view) {
        Calendar beginTime = Calendar.getInstance();
        DatePicker picker = findViewById(R.id.checkout_date);
        beginTime.set(picker.getYear(), picker.getMonth(), picker.getDayOfMonth(),9,30);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=2;COUNT=7")
                .putExtra(CalendarContract.Events.TITLE, "Aggie Blue Bikes: 2 week checkout")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Remember to bring in the bike for the 2 week checkout")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Aggie Blue Bikes Shop")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE)
                .putExtra(Intent.EXTRA_EMAIL, "aggiebluebikesagreements@gmail.com");
        startActivity(intent);
    }
}
