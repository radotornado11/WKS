package rado.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Rado on 28.12.2017.
 */

public class PhotoLoader {

    String galleryName = "/WksGallery";
    ArrayList<File> listOfImages = new ArrayList<>();
    ArrayList<Bitmap> listOfBitmaps = new ArrayList<>();

    public ArrayList<File> getPhotoFiles(String matchName){
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + galleryName + "/"+matchName;
        File dir = new File(file_path);
        if(dir.listFiles() != null)
            Collections.addAll(listOfImages, dir.listFiles());
        return listOfImages;
    }

    public ArrayList<Bitmap> convertFilesToBitmap(ArrayList<File> files){
        for(File f : listOfImages){
            File imgFile = new File(f.getPath());
            if(imgFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                listOfBitmaps.add(bitmap);
            }
        }
        return listOfBitmaps;
    }

    public ArrayList<Bitmap> getImagesFromMatch(String matchName){
        return convertFilesToBitmap(getPhotoFiles(matchName));
    }
}
