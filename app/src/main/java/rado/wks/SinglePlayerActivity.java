package rado.wks;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglePlayerActivity extends AppCompatActivity {

    TextView playerName;
    TextView playerSurname;
    ImageView playerImg;
    TextView playerNumber;
    TextView playerDate;
    TextView playerHeight;
    TextView playerWeight;
    TextView playerPos;
    TextView playerValue;
    TextView playerContract;
    TextView playerNation;
    TextView prevClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        playerImg = (ImageView) findViewById(R.id.PlayerImgView);
        playerName = (TextView) findViewById(R.id.SinglePlayerName);
        playerSurname = (TextView) findViewById(R.id.SinglePlayerSurname);
        playerNumber = (TextView) findViewById(R.id.SinglePlayerNumber);
        playerDate = (TextView) findViewById(R.id.SinglePlayerDate);
        playerHeight = (TextView) findViewById(R.id.SinglePlayerHeight);
        playerWeight = (TextView) findViewById(R.id.SinglePlayerWeight);
        playerPos = (TextView) findViewById(R.id.SinglePlayerPosition);
        playerValue = (TextView) findViewById(R.id.SinglePlayerValue);
        playerContract = (TextView) findViewById(R.id.SinglePlayerContract);
        playerNation = (TextView) findViewById(R.id.SinglePlayerNationality);
        prevClub = (TextView) findViewById(R.id.SinglePlayerPrevClub);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();

        int imageResource = getResources().getIdentifier(bundle.getString("IMG_KEY").substring(0, bundle.getString("IMG_KEY").length()-4), "drawable", getPackageName());
        Drawable image = getResources().getDrawable(imageResource);

        playerImg.setImageDrawable(image);
        playerSurname.setText(bundle.getString("SURNAME_KEY"));
        playerName.setText(bundle.getString("NAME_KEY"));
        playerNumber.setText(bundle.getString("AGE_KEY"));
        playerDate.setText(bundle.getString("DATE_KEY"));
        playerHeight.setText(bundle.getString("HEIGHT_KEY"));
        playerWeight.setText(bundle.getString("WEIGHT_KEY"));
        playerPos.setText(bundle.getString("FIELD_KEY"));
        playerValue.setText(bundle.getString("VALUE_KEY"));
        playerContract.setText(bundle.getString("CONTR_KEY"));
        playerNation.setText(bundle.getString("NATION_KEY"));
        prevClub.setText(bundle.getString("CLUB_KEY"));
    }
}
