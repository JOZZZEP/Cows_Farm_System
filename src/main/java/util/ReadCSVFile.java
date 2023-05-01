package util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSVFile {

    ArrayList<String[]> data = new ArrayList<>();
    CSVReader reader = null;
    String csvFile = "src/main/resources/data/";

    public ReadCSVFile(String fileName){
        csvFile += fileName +".csv";
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String[]> getData() {
        return data;
    }
}

