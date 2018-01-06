package rado.wks;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rado.model.ClubNameParser;
import rado.model.PhotoLoader;
import rado.model.PhotoSaver;

public class SingleEvent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView dataTextView;
    TextView timeTextView;
    TextView homeTextView;
    TextView awayTextView;

    ImageView galleryView;
    ImageView notesView;

    LinearLayout weatherLayout;
    LinearLayout navigationLayout;
    LinearLayout photoLayout;
    LinearLayout noteLayout;
    TextView weatherText;
    ClubNameParser clubNameParser;

    PhotoSaver photoSaver;
    PhotoLoader photoLoader;
    String hostCityName = "";
    String hostCityNameFileName = "";
    String guestCityNameFileName = "";

    Uri mImageUri;

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

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        clubNameParser = new ClubNameParser();
        photoSaver = new PhotoSaver();
        photoLoader = new PhotoLoader();

        dataTextView = (TextView) findViewById(R.id.SingeEventDate);
        timeTextView = (TextView) findViewById(R.id.SingleEventTime);
        homeTextView = (TextView) findViewById(R.id.SingleEventHost);
        awayTextView = (TextView) findViewById(R.id.SingleEventGuest);

        galleryView = (ImageView) findViewById(R.id.imageButton2);
        notesView = (ImageView) findViewById(R.id.imageButton3);

        Intent i = this.getIntent();
        final Bundle bundle = i.getExtras();

        dataTextView.setText(bundle.getString("DATE_KEY"));
        timeTextView.setText(bundle.getString("TIME_KEY"));
        homeTextView.setText(bundle.getString("HOME_KEY"));
        awayTextView.setText(bundle.getString("AWAY_KEY"));

        hostCityName = clubNameParser.parseClubNameToCity(bundle.getString("HOME_KEY"));
        hostCityNameFileName = clubNameParser.parseClubNameToCityWithoutPolishSigns(bundle.getString("HOME_KEY"));
        guestCityNameFileName = clubNameParser.parseClubNameToCityWithoutPolishSigns(bundle.getString("AWAY_KEY"));

        weatherLayout = (LinearLayout) findViewById(R.id.SingleEventWeatherLayout);
        navigationLayout = (LinearLayout) findViewById(R.id.SingleEventNavigationLayout);
        photoLayout = (LinearLayout) findViewById(R.id.SingleEventPhotoLayout);
        noteLayout = (LinearLayout) findViewById(R.id.SingleEventNoteLayout);

        weatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, WheaterActivity.class);
                intent.putExtra("CITY", hostCityName);
                startActivity(intent);
            }
        });

        navigationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, GoogleMapsActivity.class);
                intent.putExtra("STADION", hostCityName);
                startActivity(intent);
            }
        });

//        requestPermissionsRead();
        requestPermissionsWrite();

        photoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File photo = null;
                try
                {
                    // place where to store camera taken picture
                    photo = createTemporaryFile("picture", ".jpg");
                    photo.delete();
                }
                catch(Exception e)
                {
                    Log.v("LOL", "Can't create file to take picture!");
                    Toast.makeText(SingleEvent.this, "Please check SD card! Image shot is impossible!", Toast.LENGTH_SHORT);
                }
                mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                //start camera intent
                startActivityForResult(intent, 1);
            }
        });

        noteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, AddNoteActivity.class);
                intent.putExtra("KEY_MATCHNAME", hostCityNameFileName+guestCityNameFileName);
                startActivity(intent);
            }
        });

        galleryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, Gallery.class);
                intent.putExtra("KEY_MATCHNAME", hostCityNameFileName+guestCityNameFileName);
                startActivity(intent);
            }
        });

        notesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this, NotesActivity.class);
                intent.putExtra("KEY_MATCHNAME", hostCityNameFileName+guestCityNameFileName);
                startActivity(intent);
            }
        });
    }

    private File createTemporaryFile(String part, String ext) throws Exception
    {
        File tempDir= Environment.getExternalStorageDirectory();
        tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
        if(!tempDir.exists())
        {
            tempDir.mkdirs();
        }
        return File.createTempFile(part, ext, tempDir);
    }

    public void grabImage()
    {
        this.getContentResolver().notifyChange(mImageUri, null);
        ContentResolver cr = this.getContentResolver();
        Bitmap bitmap;
        try
        {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);
            photoSaver.saveImage(SingleEvent.this, hostCityNameFileName+guestCityNameFileName, bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d("LOL", "Failed to load", e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    grabImage();
                }
                break;
        }
    }

    private void requestPermissionsRead(){
        ActivityCompat.requestPermissions(SingleEvent.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }
    private void requestPermissionsWrite(){
        ActivityCompat.requestPermissions(SingleEvent.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                2);
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

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(SingleEvent.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
