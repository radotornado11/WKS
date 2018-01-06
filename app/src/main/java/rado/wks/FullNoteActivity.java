package rado.wks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FullNoteActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_note);

        Intent intent = getIntent();
        String text = intent.getExtras().getString("TEXT");

        textView = (TextView) findViewById(R.id.textView26);
        textView.setText(text);
    }
}
