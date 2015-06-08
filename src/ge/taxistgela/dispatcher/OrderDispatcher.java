package ge.taxistgela.dispatcher;

import com.google.gson.Gson;
import ge.taxistgela.bean.Order;
import ge.taxistgela.model.RemoteManager;
import ge.taxistgela.model.RemoteManagerAPI;

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
            System.out.println("Waiting to take order...");
            try {
                order = orders.take();
            } catch (InterruptedException ex) {
                break;
            }
            System.out.println("Took order...");

            // TODO replace with real implementation.

            final RemoteManagerAPI sessionManager = (RemoteManagerAPI) sc.getAttribute(RemoteManagerAPI.class.getName());

            if (order != null) {
                sessionManager.sendMessage(RemoteManager.DRIVER_REMOTE, Integer.toString(order.getDriverID()), new Gson().toJson(order));
            }
        }
    }

    /**
     * Add order into dispatcher queue.
     *
     * @param order
     */
    public void addToQueue(Order order) {
        System.out.println("Waiting to put order...");
        try {
            orders.put(order);
        } catch (InterruptedException e) {
            // TODO Remove
             e.printStackTrace();
        }
        System.out.println("Put order...");
    }

    /**
     * Stop dispatcher.
     */
    public void cancel() {
        state = false;
    }
}
