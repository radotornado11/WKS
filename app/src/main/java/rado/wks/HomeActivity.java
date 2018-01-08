package rado.wks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import rado.dao.DBHelper;
import rado.dao.NewsDAO;
import rado.dao.TableDAO;
import rado.dao.TimetableDAO;

public class HomeActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 2000;
    TableDAO tableDAO;
    TimetableDAO timetableDAO;
    NewsDAO newsDAO;
    String urlTable = "http://legia.com/rozgrywki/ranking/lotto-ekstraklasa-295";
    String urlTimetable = "http://pilkanozna.pl/index.php/12051/LOTTO-Ekstraklasa-2017/18/%C5%9Al%C4%85sk-Wroc%C5%82aw/showPlan.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tableDAO = new TableDAO(this);
        timetableDAO = new TimetableDAO(this);
        newsDAO = new NewsDAO(this);

        new TableRetriever().execute();
        new TimetableRetriever().execute();
        new NewsRetriever().execute();

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
                Document doc = Jsoup.connect(urlTable).get();

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

    private class TimetableRetriever extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(urlTimetable).get();

                for (Element td : doc.select("td.tide_mm")) {
                    for (Element row : td.select("tbody > tr:gt(0)")) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        Elements tds = row.select("td");

                        timetableDAO.insertData(tds.get(3).text(),tds.get(7).text(), tds.get(1).text(), tds.get(2).text(), tds.get(4).text(), tds.get(6).text());
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

    private class NewsRetriever extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String num = getNumberOfLastArticle();
                int numberOfLastArticle = Integer.valueOf(num);
                //wyviaganie headera z 1 wiadomosci ze strony
                for(int i=0;i<10;i++) {
                    Document document1 = Jsoup.connect("http://slasknet.com/?nr=" + String.valueOf(numberOfLastArticle)).get();

                    Element div2 = document1.select("div#newsik").first();

                    Element header = div2.select("article > header > h2").first();

                    if(div2.select("article > div#intertext1").first() == null){
                        numberOfLastArticle--;
                        continue;
                    }
                    Element text = div2.select("article > div#intertext1").first();

                    String head = header.text();

                    String wypo = text.text();

                    Log.d("DUPA2", head);

                    Log.d("DUPA3", wypo);
                    newsDAO.insertData(String.valueOf(numberOfLastArticle), head, wypo);
                    numberOfLastArticle--;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        private String getNumberOfLastArticle() throws IOException{
            Log.d("DZIALA", "????");

            Document document = Jsoup.connect("http://slasknet.com").get();

            Element div = document.select("div#newsik").first();

            Element subA = div.select("article > header > h2 > a").first();

            String firstNewsUrl = subA.attr("href");
            String num = firstNewsUrl.replaceAll("\\D+", "");

            Log.d("DUPA", String.valueOf(num));
            return String.valueOf(num);
        }
    }
}
