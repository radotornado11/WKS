package rado.wks;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TableListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public TableListViewAdapter(Context context,
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
        TextView rank;
        TextView team;
        TextView matches;
        TextView points;
        TextView wins;
        TextView draws;
        TextView loses;
        TextView rate;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        if(position % 2 == 0){
            itemView.setBackgroundColor(0xFF00FF00);
        }

        // Locate the TextViews in listview_item.xml
        rank = (TextView) itemView.findViewById(R.id.rankText);
        team = (TextView) itemView.findViewById(R.id.teamText);
        matches = (TextView) itemView.findViewById(R.id.matchesText);
        points = (TextView) itemView.findViewById(R.id.pointsText);
        wins = (TextView) itemView.findViewById(R.id.winsText);
        draws = (TextView) itemView.findViewById(R.id.drawsText);
        loses = (TextView) itemView.findViewById(R.id.losesText);
        rate = (TextView) itemView.findViewById(R.id.rateText);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(Table.RANK));
        team.setText(resultp.get(Table.TEAM));
        matches.setText(resultp.get(Table.MATCHES));
        points.setText(resultp.get(Table.POINTS));
        wins.setText(resultp.get(Table.WINS));
        draws.setText(resultp.get(Table.DRAWS));
        loses.setText(resultp.get(Table.LOSES));
        rate.setText(resultp.get(Table.GOALS));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        // Capture ListView item click
        return itemView;
    }
}