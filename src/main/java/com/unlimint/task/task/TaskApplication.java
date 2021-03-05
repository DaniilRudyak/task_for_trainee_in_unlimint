package com.unlimint.task.task;


import action.ConvereterForJSON;
import action.ConverterForCSV;
import model.InfoOrder;
import model.Order;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class TaskApplication {

    public static void main(String[] args) {
        List<Order> listOrderCSV = ConverterForCSV.parse(args[0]);
//        for (Order el : listOrderCSV)
//            System.out.println(el);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<Order> listOrderJSON = ConvereterForJSON.parse(args[1]);
//        for (Order el : listOrderJSON)
//            System.out.println(el);


//////////////////////////////////////////////////////////////////////////////////////////////////////////
        List<InfoOrder> infoOrderList = new ArrayList<>();
        for (Order el : listOrderCSV)
            infoOrderList.add(new InfoOrder(
                    el.getId(),
                    el.getAmount(),
                    el.getComment(),
                    args[0],
                    "OK"
            ));

        for (Order el : listOrderJSON)
            infoOrderList.add(new InfoOrder(
                    el.getId(),
                    el.getAmount(),
                    el.getComment(),
                    args[0],
                    "OK"
            ));

        for(InfoOrder el:infoOrderList)
            System.out.println(el.toString());
    }


}