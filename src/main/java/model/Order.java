package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @JsonProperty("orderId")
    private int id;

    private double amount;

    private String currency;

    private String comment;

    public Order() {
    }

    public Order(String[] strings) {
        if (strings.length == 4) {
            int i = 0;
            try {
                id = Integer.parseInt(strings[i++]);
                amount = Integer.parseInt(strings[i++]);
                currency = strings[i++];
                comment = strings[i++];
            } catch (NumberFormatException ex) {
                System.out.println("Error when converting a string: "+ strings[i-1] +" to an object field! ");
                id=-1;
            }
        } else
            throw new IllegalArgumentException("Invalid number of arguments to bean!");
    }
}
