package action;

import model.InfoOrders;
import model.Orders;

import java.util.List;
import java.util.Vector;

public class ConverterResult {
    public static void addOffer(Vector<InfoOrders> listInfoOrders, List<Orders> listOrders, String fileType) {
        for (Orders el : listOrders)
            listInfoOrders.add(new InfoOrders(
                    listInfoOrders.size() + 1,
                    el.getAmount(),
                    el.getComment(),
                    fileType,
                    "OK"
            ));
    }
}
