package com.unlimint.task;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

import action.ConverterForJSON;
import action.ConverterForCSV;
import action.ConverterResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.InfoOrders;
import model.Orders;

@SpringBootApplication

public class TaskApplication {
    private static Vector<InfoOrders> infoOrdersList = new Vector<>();

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(4);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Future<List<Orders>> listFutureCSV = service.submit(new Callable<List<Orders>>() {
            @Override
            public List<Orders> call() throws Exception {
                return ConverterForCSV.parse(args[0]);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Future<List<Orders>> listFutureJSON = service.submit(new Callable<List<Orders>>() {
            @Override
            public List<Orders> call() throws Exception {
                return ConverterForJSON.parse(args[1]);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.submit(() -> {
            try {
                ConverterResult.addOffer(infoOrdersList, listFutureCSV.get(), args[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        service.submit(() -> {
            try {
                ConverterResult.addOffer(infoOrdersList, listFutureJSON.get(), args[1]);
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
                    mapper.writeValue(System.out, infoOrdersList);
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