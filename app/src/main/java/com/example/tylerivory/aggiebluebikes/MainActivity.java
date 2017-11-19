package com.example.tylerivory.aggiebluebikes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button map = (Button)findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BikeResourceMap.class));
            }
        });
        Button updates = (Button)findViewById(R.id.updates);

        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ABBFeedActivity.class));
            }
        });
        Button checkout = (Button)findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ABBCheckoutActivity x = new ABBCheckoutActivity();
                x.setClocTextView((TextView) findViewById(R.id.textClock));

                startActivity(new Intent(MainActivity.this, x.getClass()));
            }
        });

        Button info = (Button)findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, info.class));
            }
        });


        String filecontent = readFromFile(getBaseContext());

        prepareTimers(filecontent);
        preparePhoneClick();
        int timeTillDue = (int) (TimeConstants.ONE_DAY*14); // place holder
        startTimer(timeTillDue); //to access the event thread on the UI thread


    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("duedate.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_news){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //--------------


    //sets up the button contents and the Phonecall event
    public void preparePhoneClick(){
        final Button btn = (Button) findViewById(R.id.button2);
        btn.setText("Call ABB");
        btn.setBackgroundColor(Color.CYAN);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change to their number?
                dialContactPhone("5558885555");
            }
        });
    }



    //makes a phone call to the target phoneNumber
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    //should go over the activity layout and prepare all the components.
    public void prepareTimers(String filecontent){


        TextView tv1 = (TextView) findViewById(R.id.textClock);
        TextView tv2 = (TextView) findViewById(R.id.textClock2);

        tv1.setBackgroundColor(Color.YELLOW);
        tv2.setBackgroundColor(Color.YELLOW);


        Date datedue = new Date(Long.parseLong(filecontent));

        Calendar c = Calendar.getInstance();
        Date datetoday = new Date(datedue.getTime()-TimeConstants.ONE_DAY*3);




        tv1.setText(datedue.toString().substring(0,datedue.toString().length()-9));
        tv2.setText(datetoday.toString().substring(0,datetoday.toString().length()-9));



    }

    //handles the timer and its text once the application starts
    public void startTimer(int TimeTillDue){

        //this should be the time target

        new CountDownTimer(TimeTillDue,1000) {
            @Override
            public void onTick(long l) {

                TextView tv1 = (TextView) findViewById(R.id.textClock);
                String filecontent = readFromFile(getBaseContext());
                Date dueDate = new Date(Long.parseLong(filecontent)); //duedate

                Date curDate = new Date();
                long diffMilli = (dueDate.getTime()-curDate.getTime())%(TimeConstants.ONE_DAY*14);

                TextView tv3 = (TextView) findViewById(R.id.textClock2); //update the text to display new

                //top date
                tv1.setText(dueDate.toString().substring(0,dueDate.toString().length()-9));



                String[] str = TimeConstants.formatIntTime( diffMilli);

                //seconds should go away after demo?
                tv3.setText(str[0]+" Days, "+str[1]+" Hours, "+str[2]+" Minutes " + str[3]);

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }



}
