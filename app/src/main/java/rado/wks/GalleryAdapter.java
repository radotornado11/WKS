package rado.wks;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rado on 28.12.2017.
 */

public class GalleryAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;

    public Integer[] images = {R.drawable.augusto, R.drawable.palaszewski, R.drawable.piech};
    public ArrayList<Bitmap> bitmaps = new ArrayList<>();

    public GalleryAdapter(Context c, ArrayList<Bitmap> b){
        context=c;
        bitmaps=b;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        imageView.setImageResource(images[position]);
        View gridView = convertView;
        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.gallerygridview_item, null);
        }

        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView2);
                if(bitmaps.size() == 0)
            Toast.makeText(context, "BRAK ZDJĘC", Toast.LENGTH_SHORT).show();
        else {
            imageView.setImageBitmap(bitmaps.get(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
        }
        return imageView;
    }
}
