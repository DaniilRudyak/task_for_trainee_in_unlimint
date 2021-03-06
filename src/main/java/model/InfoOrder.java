package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oracle.webservices.internal.api.message.PropertySet;
import lombok.Data;

@Data
public class InfoOrder {
    private int id;

    private double amount;

    private String comment;

    private String filename;

    private String result;

    public InfoOrder(int id, double amount, String comment, String filename, String result) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.result = result;
    }
}
