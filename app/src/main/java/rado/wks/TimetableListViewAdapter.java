package rado.wks;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.util.Log;
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

public class TimetableListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    TimetableDAO timetableDAO;
    EventDAO eventDAO;

    private int lastPosition = -1;

        public TimetableListViewAdapter(Context context,
                                ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        timetableDAO = new TimetableDAO(context);
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

            convertView = inflater.inflate(R.layout.timetablelistview_item, parent, false);
            // Locate the TextViews in listview_item.xml
            viewHolder.date = (TextView) convertView.findViewById(R.id.dateTextView);
            viewHolder.time = (TextView) convertView.findViewById(R.id.timeTextView);
            viewHolder.home_team = (TextView) convertView.findViewById(R.id.homeTeamTextView);
            viewHolder.away_team = (TextView) convertView.findViewById(R.id.awayTeamTextView);
            viewHolder.home_result = (TextView) convertView.findViewById(R.id.homeResultTextView);
            viewHolder.away_result = (TextView) convertView.findViewById(R.id.awayResultTextView);
            viewHolder.imageButton = (ImageButton) convertView.findViewById(R.id.imageButton);

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
        viewHolder.date.setText(resultp.get(TimetableDAO.DATE));
        viewHolder.time.setText(resultp.get(TimetableDAO.TIME));
        viewHolder.home_team.setText(resultp.get(TimetableDAO.TEAM_HOME));
        viewHolder.away_team.setText(resultp.get(TimetableDAO.TEAM_AWAY));
        viewHolder.home_result.setText(resultp.get(TimetableDAO.HOME_RESULT));
        viewHolder.away_result.setText(resultp.get(TimetableDAO.AWAY_RESULT));
        setEventAsEnable(resultp.get(TimetableDAO.DATE), viewHolder, position);
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                resultp = data.get(position);
                switch (v.getId()){
                    case R.id.imageButton:
                        viewHolder.imageButton.setImageResource(R.drawable.star_on);
                        eventDAO.insertData(resultp.get(eventDAO.TEAM_HOME), resultp.get(eventDAO.TEAM_AWAY), resultp.get(eventDAO.DATE), resultp.get(eventDAO.TIME), resultp.get(eventDAO.HOME_RESULT), resultp.get(eventDAO.AWAY_RESULT));
                        Toast.makeText(context, "Dodano mecz do Twoich wydarzeń", Toast.LENGTH_SHORT).show();
                        break;
            }
        }});
        viewHolder.imageButton.setTag(position);

        return convertView;
    }

    private void setEventAsEnable(String date, ViewHolder viewHolder, int position) {
        if(eventDAO.checkIfEventExists(date)) {
            viewHolder.imageButton.setImageResource(R.drawable.star_on);
        }
    }
}
