package hashcode.solution;

import hashcode.output.IAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Task {
    public final int rows;
    public final int columns;
    public final int dronesCount;
    public final int deadline;
    public final int maxLoad;
    public final List<Integer> itemWeights;
    public List<Warehouse> warehouses;
    public List<Order> orders;

    public List<IAction> actions = new ArrayList<>();

    public Task(int rows, int columns, int dronesCount, int deadline, int maxLoad, List<Integer> itemWeights,
                List<Warehouse> warehouses, List<Order> orders) {
        this.rows = rows;
        this.columns = columns;
        this.dronesCount = dronesCount;
        this.deadline = deadline;
        this.maxLoad = maxLoad;
        this.itemWeights = itemWeights;
        this.warehouses = warehouses;
        this.orders = orders;
        Collections.sort(this.orders);
    }
}
