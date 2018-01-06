package rado.wks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.BitSet;

import rado.model.PhotoLoader;

public class FullImageGalleryActivity extends AppCompatActivity {

    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    PhotoLoader photoLoader = new PhotoLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_gallery);

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("id");
        bitmaps = photoLoader.getImagesFromMatch(intent.getExtras().getString("match"));

        GalleryAdapter galleryAdapter = new GalleryAdapter(this, bitmaps);

        ImageView imageView = (ImageView) findViewById(R.id.fullImageView);
        imageView.setImageBitmap(galleryAdapter.bitmaps.get(position));
    }
}
