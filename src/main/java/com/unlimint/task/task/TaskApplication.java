package com.unlimint.task.task;


import action.ConverterForJSON;
import action.ConverterForCSV;
import model.InfoOrder;
import model.Order;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootApplication

public class TaskApplication {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Order> listOrderCSV = null;
        List<Order> listOrderJSON = null;
        ExecutorService service = Executors.newFixedThreadPool(4);

        Future<List<Order>> listFutureCSV = service.submit(new Callable<List<Order>>() {
            @Override
            public List<Order> call() throws Exception {
                return ConverterForCSV.parse(args[0]);

            }
        });


////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Future<List<Order>> listFutureJSON = service.submit(new Callable<List<Order>>() {
            @Override
            public List<Order> call() throws Exception {
                return ConverterForJSON.parse(args[1]);

            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////

        listOrderCSV = listFutureCSV.get();
        listOrderJSON = listFutureJSON.get();
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
                    args[1],
                    "OK"
            ));

        for (InfoOrder el : infoOrderList)
            System.out.println(el.toString());

        service.shutdown();
    }


}