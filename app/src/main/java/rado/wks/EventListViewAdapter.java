package rado.wks;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import rado.dao.EventDAO;
import rado.dao.TimetableDAO;

/**
 * Created by Rado on 07.11.2017.
 */

public class EventListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    HashMap<String, String> currentResultp = new HashMap<String, String>();
    EventDAO eventDAO;

    private int lastPosition = -1;

    public EventListViewAdapter(Context context,
                                    ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        eventDAO = new EventDAO(context);
    }

    private static class ViewHolder {
        ImageButton imageButton;
        TextView date;
        TextView time;
        TextView home_team;
        TextView away_team;
        TextView home_result;
        TextView away_result;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        final ViewHolder viewHolder;
        final View itemView;
        resultp = data.get(position);

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.eventlistview_item, parent, false);
            // Locate the TextViews in listview_item.xml
            viewHolder.date = (TextView) convertView.findViewById(R.id.dateTextView);
            viewHolder.time = (TextView) convertView.findViewById(R.id.timeTextView);
            viewHolder.home_team = (TextView) convertView.findViewById(R.id.homeTeamTextView);
            viewHolder.away_team = (TextView) convertView.findViewById(R.id.awayTeamTextView);
            viewHolder.home_result = (TextView) convertView.findViewById(R.id.homeResultTextView);
            viewHolder.away_result = (TextView) convertView.findViewById(R.id.awayResultTextView);

            itemView = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            itemView = convertView;
        }

        lastPosition = position;

        if (position % 2 == 0) {
            convertView.setBackgroundColor(0xFF00FF00);
        }
        else
            convertView.setBackgroundColor(0xFFFFFFFF);

        // Capture position and set results to the TextViews
        viewHolder.date.setText(resultp.get(EventDAO.DATE));
        viewHolder.time.setText(resultp.get(EventDAO.TIME));
        viewHolder.home_team.setText(resultp.get(EventDAO.TEAM_HOME));
        viewHolder.away_team.setText(resultp.get(EventDAO.TEAM_AWAY));
        viewHolder.home_result.setText(resultp.get(EventDAO.HOME_RESULT));
        viewHolder.away_result.setText(resultp.get(EventDAO.AWAY_RESULT));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentResultp = data.get(position);
                openEventDetails(currentResultp.get(EventDAO.DATE),
                                 currentResultp.get(EventDAO.TEAM_HOME),
                                 currentResultp.get(EventDAO.TEAM_AWAY),
                                 currentResultp.get(EventDAO.TIME));
            }
        });

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        // Capture ListView item click

        return convertView;
    }

    private void openEventDetails(String date, String teamHome, String teamAway, String time) {
        Intent i = new Intent(context, SingleEvent.class);
        i.putExtra("DATE_KEY", date);
        i.putExtra("TIME_KEY", time);
        i.putExtra("HOME_KEY", teamHome);
        i.putExtra("AWAY_KEY", teamAway);

        context.startActivity(i);
    }
}
