package proiect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/*insiruirea logica a programului este urmatoarea:

* citesc fisierul de intrare iar din acesta iau numele oraselor si indicativul tarilor pe care le afisez in GUI
* downloadez fisierul json asociat cererii utilizatorului
* parsez parametrii de interes si ii salvez in UIBridge pentru a fi mai usor de afisat
* creez interfata specifica datelor primite
*
* */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {



     /*   downloader a = new downloader("London","uk");
        String currpath=a._do();
        jsonParser parserr=new jsonParser(currpath);
        parserr._parse();*/
    InFileReader a=new InFileReader();
    a.readFile();
    a.makeCountryList();
        launch(args);

    }
}
