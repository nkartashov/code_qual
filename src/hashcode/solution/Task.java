package hashcode.solution;

import hashcode.output.DeliverAction;
import hashcode.output.IAction;
import hashcode.output.LoadAction;
import hashcode.output.WaitAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public List<Drone> drones;
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
        this.drones = new ArrayList<>();
        for (int i = 0; i < dronesCount; i++) {
            this.drones.add(new Drone(i, warehouses.get(0).row, warehouses.get(0).column, this));
        }
    }

    public void deliver(Drone drone, Order order, int type, int count) throws DeadlineHit {
        int timeToCustomer = order.timeToFly(drone);

        drone.moveTime(timeToCustomer + 1);
        drone.setLocation(order);
        drone.deliver(type, count);
        order.deliver(type, count);
        actions.add(new DeliverAction(drone.id, order.id, type, count));
    }

    public void wait(Drone drone, int time) throws DeadlineHit {
        drone.moveTime(time);
        actions.add(new WaitAction(drone.id, time));
    }

    public void load(Drone drone, Warehouse warehouse, int type, int count) throws DeadlineHit {
        int timeToWarehouse = drone.timeToFly(warehouse);

        drone.moveTime(timeToWarehouse + 1);
        drone.setLocation(warehouse);
        drone.load(type, count);
        warehouse.updateState(type, -count);
        actions.add(new LoadAction(drone.id, warehouse.id, type, count));
    }

    public Drone getClosest(Warehouse warehouse) {
        return Collections.min(drones, (o1, o2) -> o1.timeTo(warehouse) - o2.timeTo(warehouse));
    }
}
