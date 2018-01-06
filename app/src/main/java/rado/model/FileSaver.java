package rado.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Rado on 06.01.2018.
 */

public class FileSaver {
    String filesGalleryName = "/WksNotes";
    Context context;

    public void saveNote(Context c, String matchName, String title, String text){
        this.context =c;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + filesGalleryName + "/"+matchName;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, title+ "_" + CurrentDateAndTime + ".txt");
        try{
            FileOutputStream fOut = new FileOutputStream(file);
            fOut.write(text.getBytes());
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
