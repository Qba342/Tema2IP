package proiect;

import java.io.File;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

public class historyCreater {
    public ArrayList<String> a=new ArrayList<String>();


    public  void initArray() {
        File folder=new File("history/");
        File[] allfiles=folder.listFiles();

        for(File b :allfiles)
        {
            a.add(b.getName());
        }


}
}
