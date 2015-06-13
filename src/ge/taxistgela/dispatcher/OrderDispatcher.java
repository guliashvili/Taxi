package ge.taxistgela.dispatcher;

import com.google.gson.Gson;
import ge.taxistgela.bean.Order;
import ge.taxistgela.helper.ExternalAlgorithms;
import ge.taxistgela.model.SessionManager;
import ge.taxistgela.model.SessionManagerAPI;

import javax.servlet.ServletContext;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alex on 6/5/2015.
 */
public class OrderDispatcher extends Thread {

    private static final int QUEUE_LIMIT = 1000;

    private final ServletContext sc;
    private final BlockingQueue<Order> orders;
    private boolean state;

    public OrderDispatcher(ServletContext sc) {
        this.sc = sc;
        this.orders = new ArrayBlockingQueue<>(QUEUE_LIMIT);
        this.state = true;
    }

    @Override
    public void run() {
        while (state) {
            if (isInterrupted())
                break;

            Order order = null;
            ExternalAlgorithms.debugPrint("Waiting to take order...");

            try {
                order = orders.take();
            } catch (InterruptedException ex) {
                break;
            }

            ExternalAlgorithms.debugPrint("Took order...");

            // TODO replace with real implementation.

            final SessionManagerAPI sessionManager = (SessionManagerAPI) sc.getAttribute(SessionManagerAPI.class.getName());

            if (order != null) {
                sessionManager.sendMessage(SessionManager.DRIVER_SESSION, Integer.toString(order.getDriverID()), new Gson().toJson(order));
            }
        }

        ExternalAlgorithms.debugPrint("Order dispatcher stopped...");
    }

    /**
     * Add order into dispatcher queue.
     *
     * @param order
     */
    public void addToQueue(Order order) {
        ExternalAlgorithms.debugPrint("Waiting to put order...");

        try {
            orders.put(order);
        } catch (InterruptedException e) {
            // TODO Remove
             e.printStackTrace();
        }

        ExternalAlgorithms.debugPrint("Put order...");
    }

    /**
     * Stop dispatcher.
     */
    public void cancel() {
        ExternalAlgorithms.debugPrint("Canceling order dispatcher...");
        state = false;
    }
}
