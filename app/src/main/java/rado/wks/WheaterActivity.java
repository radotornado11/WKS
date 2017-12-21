package rado.wks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WheaterActivity extends AppCompatActivity {

    TextView temp, city, description, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheater);

        temp = (TextView) findViewById(R.id.weatherTemp);
        city = (TextView) findViewById(R.id.weatherCity);
        description = (TextView) findViewById(R.id.weatherDesc);
        date = (TextView) findViewById(R.id.weatherDate);

        Intent i = this.getIntent();
        final Bundle bundle = i.getExtras();

        findWeather(bundle.getString("CITY"));
    }

    public void findWeather(final String cityName){
        String apiKey= "8a810bb8602637cbc658131583b7290a";
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID=8a810bb8602637cbc658131583b7290a";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mainObject = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temperatureRes = String.valueOf(mainObject.getDouble("temp"));
                    String descriptionRes = object.getString("description");
                    String cityRes = response.getString("name");

                    city.setText(cityRes);
                    description.setText(descriptionRes);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    String formattedDate = sdf.format(calendar.getTime());

                    double tempInt = Double.parseDouble(temperatureRes);
                    double centi = tempInt - 273.15;
                    int i = (int) centi;
                    temp.setText(String.valueOf(i));

                    date.setText(formattedDate);
                }catch (JSONException j){
                    j.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}
