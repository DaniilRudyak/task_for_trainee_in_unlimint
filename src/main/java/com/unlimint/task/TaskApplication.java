package com.unlimint.task;


import action.ConverterForJSON;
import action.ConverterForCSV;
import action.ConverterResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.InfoOrder;
import model.Order;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

@SpringBootApplication

public class TaskApplication {
    private static Vector<InfoOrder> infoOrderList = new Vector<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(4);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.submit(() -> {
            try {
                ConverterResult.addOffer(infoOrderList, listFutureCSV.get(), args[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.submit(() -> {
            try {
                ConverterResult.addOffer(infoOrderList, listFutureJSON.get(), args[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.submit(() -> {
            try {
                if (listFutureJSON.get().size() != 0 || listFutureCSV.get().size() != 0) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(System.out, infoOrderList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.shutdown();
    }
}