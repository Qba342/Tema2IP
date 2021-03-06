package proiect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class downloader {
    private String nume_oras;
    private String indicativ_tara;


    public downloader(String nume_oras, String indicativ_tara) {
        this.nume_oras = nume_oras;
        this.indicativ_tara = indicativ_tara;
    }

    public String _do() throws IOException {
        /*
        Vom downloada rezultatul interogarii api-ului de openweathermap. Pentru utilizarea cu acuratete a acestuia, vom avea nevoie de numele orasului si indicativul
        din care face parte tara respectiva. Rezultatul obtinut va fi de tip json si va fi salvat intr-un fisier (pentru a putea realiza istoricul).

         */
        String url=String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,%s&APPID=52e61ecd70be254f210dea5145025866",nume_oras,indicativ_tara);
       // String pt=String.format("history/%s/%s/",indicativ_tara,nume_oras);
      //  Path path = Paths.get(pt);
      /*  if (!Files.exists(path))
        {
            Files.createDirectories(path);
        }*/
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            String deRet="history/"+System.currentTimeMillis()+"."+indicativ_tara+"."+nume_oras;
            FileOutputStream fos = new FileOutputStream(deRet);

            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            return deRet;           //acest string este folosit pentru a avea in memorie cautarea actuala.
        }
        catch (Exception e){
            e.getStackTrace();
            return null;

        }

    }


}
