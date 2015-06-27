package ge.taxistgela.ram.bean;

/**
 * Created by GIO on 6/27/2015.
 */
public class userInfo {
    public Object lock = new Object();
    private double cost = 0;
    private double timeLimit;


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void addCost(double addCost) {
        this.cost += addCost;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }
}
