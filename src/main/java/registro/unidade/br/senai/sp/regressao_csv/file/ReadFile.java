package regressao_csv.file;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public List<double[]> readCSV(){

        List<double[]> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(new CSVParserBuilder().withSeparator(';')
                        .build())
                .build()) {

            String[] nextLine;
            boolean firstLine = true;

            while ((nextLine = csvReader.readNext()) != null){

                //Pular primeira linha (rotulo)
                if(firstLine){
                    firstLine = false;
                    continue;
                }

                double x = Double.parseDouble(nextLine[0].trim());
                double y = Double.parseDouble(nextLine[1].trim());
                data.add(new double[]{x, y});

            }


        }catch (IOException | CsvValidationException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return data;

    }


}
