package action;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Orders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterForCSV {
    public static List<Orders> parse(String filePath) {
        CSVReader reader = null;
        List<Orders> list = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader("orders1.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String[] strings;
            while ((strings = reader.readNext()) != null) {
                Orders orders = new Orders(strings);
                if (orders.getId() != -1)
                    list.add(new Orders(strings));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return list;
    }
}
