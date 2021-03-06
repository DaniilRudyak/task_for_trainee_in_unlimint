package action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterForJSON {
    public static List<Order> parse(String filePath) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        List<Order> listOrder = new ArrayList<>();
        List<String> json = new ArrayList<>();


        try {
            String str;
            while (((str = bufferedReader.readLine()) != null) && (!str.equals("]"))) {
                str = str.replace("[", "");
                str = str.replace("]", "");
                if (str.charAt(str.length() - 1) == ',')
                    str = str.substring(0, str.length() - 1);
                json.add(str);
            }
            for (String el : json) {
                Order order = convert(el);
                if (order != null)
                    listOrder.add(order);
            }


        } catch (IOException e) {
           e.printStackTrace();
        }
        return listOrder;
    }

    private static Order convert(String order) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(order, Order.class);
        } catch (JsonProcessingException e) {
            System.out.println("An error occurred while converting JSON to bean: " + order);
        }
        return null;
    }
}
