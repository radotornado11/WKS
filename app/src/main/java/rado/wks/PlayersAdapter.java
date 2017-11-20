package rado.wks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rado.model.Player;

/**
 * Created by Rado on 14.11.2017.
 */

public class PlayersAdapter extends BaseAdapter {

    private  int icons[];

    private ArrayList<Player> names;

    private Context context;

    private LayoutInflater inflater;

    public PlayersAdapter(Context context/*, int icons[],*/, ArrayList<Player> names){
        this.context = context;
        /*this.icons = icons;*/
        this.names = names;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;

        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.teamgridview_item, null);
        }

        /*ImageView icon = (ImageView) gridView.findViewById(R.id.imageView2);*/
        TextView name = (TextView) gridView.findViewById(R.id.textView9);

        /*icon.setImageResource(icons[position]);*/

        name.setText(names.get(position).getName()+ " " + names.get(position).getSurname());

        return gridView;
    }
}
