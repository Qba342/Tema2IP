package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileReader {

    private List<inFile> records = new ArrayList<inFile>();
    private List<String> countryList=new ArrayList<String>();


    private void readFile() throws FileNotFoundException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("inFile.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] _split=line.split(" ");
                inFile aux=new inFile();
                aux.nume=_split[0];
                aux.cod=_split[1];
                records.add(aux);
            }
            reader.close();
          //  return records;
        } catch (IOException e) {
            e.printStackTrace();
          //  return null;
        }
    }


    public void makeCountryList(){




        for(inFile a:records)
        {
            if(!countryList.contains(a.cod))
            {
                countryList.add(a.cod);
            }
        }


    }
    public void init() throws FileNotFoundException {
        readFile();
        makeCountryList();
    }

    public List<inFile> getRecords() {
        return records;
    }

    public List<String> getCountryList() {
        return countryList;
    }
}
