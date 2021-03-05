package action;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Order;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterForCSV {
    public static List<Order> parse(String filePath) {
        CSVReader reader = null;
        List<Order> list = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader("orders1.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            String[] strings;
            while ((strings = reader.readNext()) != null) {
                Order order = new Order(strings);
                if(order.getId()!=-1)
                list.add(new Order(strings));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return list;
    }

}
