package action;

import model.InfoOrder;
import model.Order;

import java.util.List;
import java.util.Vector;

public class ConverterResult {
    public static void addOffer(Vector<InfoOrder> listInfoOrder, List<Order> listOrder, String fileType) {
        for (Order el : listOrder)
            listInfoOrder.add(new InfoOrder(
                    listInfoOrder.size() + 1,
                    el.getAmount(),
                    el.getComment(),
                    fileType,
                    "OK"
            ));
    }
}
