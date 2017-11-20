package rado.wks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import rado.model.Player;

public class Team extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridView gridView;
    GridView gridView2;
    GridView gridView3;
    GridView gridView4;
    PlayersAdapter playersAdapter;
    PlayersAdapter playersAdapter2;
    PlayersAdapter playersAdapter3;
    PlayersAdapter playersAdapter4;
    ArrayList<Player> players;
    ArrayList<Player> kippers;
    ArrayList<Player> defenders;
    ArrayList<Player> midfielders;
    ArrayList<Player> attackers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(4).setChecked(true);

        gridView = (GridView) findViewById(R.id.bramkarzeGrid);
        gridView2 = (GridView) findViewById(R.id.obroncyGrid);
        gridView3 = (GridView) findViewById(R.id.pomocnicyGrid);
        gridView4 = (GridView) findViewById(R.id.napastnicyGrid);

        players = generatePlayersList();

        kippers = getPlayersOnPosition("Bramkarz");
        defenders = getPlayersOnPosition("Obrońca");
        midfielders = getPlayersOnPosition("Pomocnik");
        attackers = getPlayersOnPosition("Napastnik");

        playersAdapter = new PlayersAdapter(Team.this, kippers);
        playersAdapter2 = new PlayersAdapter(Team.this, defenders);
        playersAdapter3 = new PlayersAdapter(Team.this, midfielders);
        playersAdapter4 = new PlayersAdapter(Team.this, attackers);

        gridView.setAdapter(playersAdapter);
        gridView2.setAdapter(playersAdapter2);
        gridView3.setAdapter(playersAdapter3);
        gridView4.setAdapter(playersAdapter4);
    }

    private Player generatePlayer(ArrayList<String> playerTrait) {
        return new Player(playerTrait.get(0), playerTrait.get(1),playerTrait.get(2), playerTrait.get(3), playerTrait.get(4),
                playerTrait.get(5), playerTrait.get(6), playerTrait.get(7),playerTrait.get(8), playerTrait.get(9), playerTrait.get(10));
    }

    private String[] getListOfFilesNameFromAssets()
    {
        String[] list;
        try {
            list = getAssets().list("");
            return list;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Player> generatePlayersList()
    {
        ArrayList<Player> playersList = new ArrayList<>();
        String[] playersFilesList;
        try {
            playersFilesList = getListOfFilesNameFromAssets();
            for(String playerFileName : playersFilesList) {
                if(playerFileName.substring(playerFileName.length()-3).equals("txt")) {
                    InputStream fstream = getAssets().open(playerFileName);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    ArrayList<String > playerTrait = new ArrayList<>();
                    while ((strLine = br.readLine()) != null) {
                        // Print the content on the console
                        playerTrait.add(strLine);
                    }
                    Player p = generatePlayer(playerTrait);
                    playersList.add(p);
                }
            }
            return playersList;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Player> getPlayersOnPosition(String position)
    {
        ArrayList<Player> playersOnPosition = new ArrayList<>();
        for(int i=0; i<players.size();i++)
        {
            if(players.get(i).getPosition().equalsIgnoreCase(position))
                playersOnPosition.add(players.get(i));
        }
        return playersOnPosition;
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
                Intent g = new Intent(this, Timetable.class);
                startActivity(g);
                break;
            case R.id.nav_team:
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
