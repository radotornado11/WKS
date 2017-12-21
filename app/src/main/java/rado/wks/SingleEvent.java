package rado.wks;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import rado.model.ClubNameParser;

public class SingleEvent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView dataTextView;
    TextView timeTextView;
    TextView homeTextView;
    TextView awayTextView;

    LinearLayout weatherLayout;
    TextView weatherText;
    ClubNameParser clubNameParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        clubNameParser = new ClubNameParser();

        dataTextView = (TextView) findViewById(R.id.SingeEventDate);
        timeTextView = (TextView) findViewById(R.id.SingleEventTime);
        homeTextView = (TextView) findViewById(R.id.SingleEventHost);
        awayTextView = (TextView) findViewById(R.id.SingleEventGuest);

        Intent i = this.getIntent();
        final Bundle bundle = i.getExtras();

        dataTextView.setText(bundle.getString("DATE_KEY"));
        timeTextView.setText(bundle.getString("TIME_KEY"));
        homeTextView.setText(bundle.getString("HOME_KEY"));
        awayTextView.setText(bundle.getString("AWAY_KEY"));

        final String hostCityName = clubNameParser.parseClubNameToCity(bundle.getString("HOME_KEY"));
        Log.d("DUPA", hostCityName);

        weatherLayout = (LinearLayout) findViewById(R.id.SingleEventWeatherLayout);

        weatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, WheaterActivity.class);
                intent.putExtra("CITY", hostCityName);
                startActivity(intent);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.single_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_main:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.nav_news:
                Intent h = new Intent(this, News.class);
                startActivity(h);
                break;
            case R.id.nav_table:
                Intent i = new Intent(this, Table.class);
                startActivity(i);
                break;
            case R.id.nav_timetable:
                Intent g = new Intent(this, Timetable.class);
                startActivity(g);
                break;
            case R.id.nav_team:
                Intent s = new Intent(this, Team.class);
                startActivity(s);
                break;
            case R.id.nav_club:
                Intent t = new Intent(this, Club.class);
                startActivity(t);
                break;
            case R.id.nav_shop:
                Intent sh = new Intent(this, MyEvent.class);
                startActivity(sh);
                break;
            case R.id.nav_gallery:
                Intent gal = new Intent(this, Gallery.class);
                startActivity(gal);
                break;
            case R.id.nav_chants:
                Intent chants = new Intent(this, Chants.class);
                startActivity(chants);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
