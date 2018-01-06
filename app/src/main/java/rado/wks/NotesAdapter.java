package rado.wks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Rado on 06.01.2018.
 */

public class NotesAdapter extends BaseAdapter {

    public ArrayList<File> noteFiles = new ArrayList<>();
    private Context context;
    LayoutInflater inflater;

    public NotesAdapter(Context c, ArrayList<File> files) {
        context = c;
        noteFiles = files;
    }
    @Override
    public int getCount() {
        return noteFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return noteFiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View gridView = convertView;
        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.notesview_item, null);
        }

        TextView textView = (TextView) gridView.findViewById(R.id.textView5);
        if(noteFiles.size() == 0)
            Toast.makeText(context, "BRAK ZDJĘC", Toast.LENGTH_SHORT).show();
        else {
            textView.setText(getTitleFromFileName(noteFiles.get(position).getName()));
        }
        return gridView;
    }

    private String getTitleFromFileName(String fileName){
        return fileName.substring(0, fileName.lastIndexOf("_"));
    }
}
