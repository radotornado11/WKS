package rado.wks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Timetable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    TimetableListViewAdapter adapter;
    TextView textView;
    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> arrayList;
    static String DATE = "Data";
    static String TIME = "Czas";
    static String TEAM_HOME = "Drużyna dom";
    static String TEAM_AWAY = "Drużyna wyjazd";
    static String HOME_RESULT = "Bramki dom";
    static String AWAY_RESULT = "Bramki wyjazd";

    String url = "http://pilkanozna.pl/index.php/12051/LOTTO-Ekstraklasa-2017/18/%C5%9Al%C4%85sk-Wroc%C5%82aw/showPlan.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(3).setChecked(true);

        new TimetableRetriever().execute();
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
        getMenuInflater().inflate(R.menu.main, menu);
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
                Intent main= new Intent(this,MainActivity.class);
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

    private class TimetableRetriever extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Timetable.this);
            progressDialog.setTitle("Terminarz WKS");
            progressDialog.setMessage("Wczytywanie...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            arrayList = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(url).get();

                for (Element td : doc.select("td.tide_mm")) {
                    for (Element row : td.select("tbody > tr:gt(0)")) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        Elements tds = row.select("td");

                        hashMap.put(DATE, tds.get(1).text());
                        hashMap.put(TIME, tds.get(2).text());
                        hashMap.put(TEAM_HOME, tds.get(3).text());
                        hashMap.put(TEAM_AWAY, tds.get(7).text());
                        hashMap.put(HOME_RESULT, tds.get(4).text());
                        hashMap.put(AWAY_RESULT, tds.get(6).text());

                        arrayList.add(hashMap);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listView = (ListView) findViewById(R.id.listView);
            adapter = new TimetableListViewAdapter(Timetable.this, arrayList);
            listView.setAdapter(adapter);
            progressDialog.dismiss();
        }
    }
}
