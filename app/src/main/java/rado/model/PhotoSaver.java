package rado.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rado on 28.12.2017.
 */

public class PhotoSaver {
    String galleryName = "/WksGallery";
    Context context;

    public void saveImage(Context context, String matchName, Bitmap imageToSave){
        this.context = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + galleryName + "/"+matchName;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, "matchName_" + CurrentDateAndTime + ".jpg");
        try{
            FileOutputStream fOut = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG,85,fOut);
            fOut.flush();
            fOut.close();
        }
        catch(FileNotFoundException e){ e.printStackTrace();}
        catch(IOException e){ e.printStackTrace();}
    }

    private String getCurrentDateAndTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
}
