package model;

import lombok.Data;

@Data
public class InfoOrders {
    private int id;

    private double amount;

    private String comment;

    private String filename;

    private String result;

    public InfoOrders(int id, double amount, String comment, String filename, String result) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.result = result;
    }
}
