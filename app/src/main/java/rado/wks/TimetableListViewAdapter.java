package rado.wks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rado on 07.11.2017.
 */

public class TimetableListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public TimetableListViewAdapter(Context context,
                                ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView date;
        TextView time;
        TextView home_team;
        TextView away_team;
        TextView home_result;
        TextView away_result;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.timetablelistview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        if(position % 2 == 0){
            itemView.setBackgroundColor(0xFF00FF00);
        }

        // Locate the TextViews in listview_item.xml
        date = (TextView) itemView.findViewById(R.id.dateTextView);
        time = (TextView) itemView.findViewById(R.id.timeTextView);
        home_team = (TextView) itemView.findViewById(R.id.homeTeamTextView);
        away_team = (TextView) itemView.findViewById(R.id.awayTeamTextView);
        home_result = (TextView) itemView.findViewById(R.id.homeResultTextView);
        away_result = (TextView) itemView.findViewById(R.id.awayResultTextView);


        // Capture position and set results to the TextViews
        date.setText(resultp.get(Timetable.DATE));
        time.setText(resultp.get(Timetable.TIME));
        home_team.setText(resultp.get(Timetable.TEAM_HOME));
        away_team.setText(resultp.get(Timetable.TEAM_AWAY));
        home_result.setText(resultp.get(Timetable.HOME_RESULT));
        away_result.setText(resultp.get(Timetable.AWAY_RESULT));

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        // Capture ListView item click
        return itemView;
    }
}
