package rado.wks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GoogleMapsActivity extends AppCompatActivity {

    private Intent googleMapIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        Intent i = this.getIntent();
        final Bundle bundle = i.getExtras();

        initialize(bundle.getString("STADION"));
    }

    private void initialize(String cityName){
        googleMapIntent = new Intent(Intent.ACTION_VIEW);
        googleMapIntent.setData(Uri.parse("geo:0,0?q="+cityName+", Poland stadion"));
        this.startActivity(googleMapIntent);
    }
}
