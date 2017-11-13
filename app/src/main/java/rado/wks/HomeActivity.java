package rado.wks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import rado.dao.DBHelper;
import rado.dao.TableDAO;

public class HomeActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 1000;
    TableDAO tableDAO;
    String url = "http://legia.com/rozgrywki/ranking/lotto-ekstraklasa-295";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tableDAO = new TableDAO(this);

        new TableRetriever().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }

    private class TableRetriever extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(url).get();

                for (Element table : doc.select("table#kolejka_0")) {
                    for (Element row : table.select("tbody > tr")) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        Elements tds = row.select("td");

                        tableDAO.insertData(tds.get(0).text(), tds.get(1).text(), tds.get(2).text(), tds.get(3).text(), tds.get(4).text(),
                                tds.get(5).text(), tds.get(6).text(), tds.get(7).text());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }
}
