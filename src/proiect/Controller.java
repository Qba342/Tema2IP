package proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String countryName;
    private String cityName;
   @FXML  private ComboBox country;
   @FXML private ComboBox cty;
   @FXML private Text umid;
   @FXML private Text wind;
   @FXML private ImageView img;
   @FXML private Text gr;
   @FXML private Text vreme;
   @FXML private Text dtt;
   @FXML private ComboBox hist;

   @FXML protected void setCity()
   {
       this.cityName= (String) cty.getValue();
   }

   @FXML protected void setCountry(){
       this.countryName= (String) country.getValue();
       ArrayList<String> aux=new ArrayList<String>();
       for(inFile a: rd.getRecords()){
           if(a.cod.equals(countryName))
           {
               aux.add(a.nume);
           }
       }


       ObservableList<String> list2= FXCollections.observableArrayList(aux);
       cty.setItems(list2);



   }


   @FXML protected void mainAction() throws IOException {
       downloader a=new downloader(this.cityName,this.countryName);
       String currpath=a._do();
       jsonParser parserr=new jsonParser(currpath);
       UIBridge interest_values=new UIBridge();
       interest_values=parserr._parse();
       umid.setText(String.valueOf(interest_values.humidity));
       if(interest_values.wind_speed!=null)
       wind.setText(String.valueOf(interest_values.wind_speed));
      else if(interest_values.wind_speed2!=null)
           wind.setText(String.valueOf(interest_values.wind_speed2));
       String imLink=String.format("http://openweathermap.org/img/wn/%s@4x.png",interest_values.imgCode);
       Image image=new Image(imLink);
       img.setImage(image);
       gr.setText(String.valueOf(Math.round(interest_values.temp)-273)+"°C");
       vreme.setText(interest_values.weather);
       Date date=new Date();
       SimpleDateFormat ft=new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss");
       dtt.setText(ft.format(date));

   }

   @FXML protected void triggerHistory()
   {
       historyCreater his=new historyCreater();
       String ceva= (String) hist.getValue();
       Long data= Long.valueOf(ceva.split("\\.")[0]);


       jsonParser parserr=new jsonParser("history/"+hist.getValue());
       UIBridge interest_values=new UIBridge();
       interest_values=parserr._parse();
       umid.setText(String.valueOf(interest_values.humidity));
       if(interest_values.wind_speed!=null)
           wind.setText(String.valueOf(interest_values.wind_speed));
       else if(interest_values.wind_speed2!=null)
           wind.setText(String.valueOf(interest_values.wind_speed2));
       String imLink=String.format("http://openweathermap.org/img/wn/%s@4x.png",interest_values.imgCode);
       Image image=new Image(imLink);
       img.setImage(image);
       gr.setText(String.valueOf(Math.round(interest_values.temp)-273)+"°C");
       vreme.setText(interest_values.weather);
       Date date=new Date(data);
       SimpleDateFormat ft=new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss");
       dtt.setText(ft.format(date));


   }


   private InFileReader rd;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rd=new InFileReader();
        try {
            rd.init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        ObservableList<String> list= FXCollections.observableArrayList(rd.getCountryList());
        country.setItems(list);

        historyCreater a=new historyCreater();
        a.initArray();
        //System.out.println(a.a);

        ObservableList<String> lista2= FXCollections.observableArrayList(a.a);
        hist.setItems(lista2);
      /*
        for(String str:a.a)
        {
            String[] aux3=str.split(".");

            System.out.println(aux3);

        }
        System.out.println(lista);
      */









    }
}
