package rado.wks;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View gridView = convertView;

        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.teamgridview_item, null);
        }

        int imageResource = context.getResources().getIdentifier(names.get(position).getImg().substring(0, names.get(position).getImg().length()-4), "drawable", context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);

        TextView name = (TextView) gridView.findViewById(R.id.textView9);
        ImageView icon = (ImageView) gridView.findViewById(R.id.imageView2);

        icon.setImageDrawable(image);
        name.setText(names.get(position).getName()+ " " + names.get(position).getSurname());

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player currPlayer = names.get(position);
                openPlayerDetails(currPlayer.getName(), currPlayer.getSurname(), currPlayer.getNumber(), currPlayer.getDate(),
                                    currPlayer.getHeight(), currPlayer.getWeight(), currPlayer.getPosition(), currPlayer.getMarketValue(),
                                    currPlayer.getEndOfContractDate(), currPlayer.getNationality(), currPlayer.getPreviousClub(), currPlayer.getImg());

            }
        });

        return gridView;
    }

    private void openPlayerDetails(String name, String surname, String number, String date, String height, String weight, String fieldPos,
                                   String value, String enfOfContr, String nationality, String prevClub, String img) {
        Intent i = new Intent(context, SinglePlayerActivity.class);
        i.putExtra("NAME_KEY", name);
        i.putExtra("SURNAME_KEY", surname);
        i.putExtra("AGE_KEY", number);
        i.putExtra("DATE_KEY", date);
        i.putExtra("HEIGHT_KEY", height);
        i.putExtra("WEIGHT_KEY", weight);
        i.putExtra("FIELD_KEY", fieldPos);
        i.putExtra("VALUE_KEY", value);
        i.putExtra("CONTR_KEY", enfOfContr);
        i.putExtra("NATION_KEY", nationality);
        i.putExtra("CLUB_KEY", prevClub);
        i.putExtra("IMG_KEY", img);
        context.startActivity(i);
    }
}
