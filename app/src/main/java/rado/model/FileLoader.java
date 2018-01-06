package rado.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Rado on 06.01.2018.
 */

public class FileLoader {

    String filesGalleryName = "/WksNotes";
    ArrayList<File> listOfNotes = new ArrayList<>();

    public ArrayList<File> getImageFiles(String matchName){
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + filesGalleryName + "/"+matchName;
        File dir = new File(file_path);
        if(dir.listFiles() != null)
            Collections.addAll(listOfNotes, dir.listFiles());
        return listOfNotes;
    }

    public ArrayList<File> getNotesFromMatch(String matchName){
        return getImageFiles(matchName);
    }
}
