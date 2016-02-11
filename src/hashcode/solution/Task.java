package hashcode.solution;

import hashcode.output.DeliverAction;
import hashcode.output.IAction;
import hashcode.output.LoadAction;
import hashcode.output.WaitAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

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
            this.drones.add(new Drone(warehouses.get(0).m_row, warehouses.get(0).m_column));
        }
    }

    public void deliver(int droneId, int orderId, int productTypeId, int itemsToDeliver) {
        Order order = orders.get(orderId);
        Drone drone = drones.get(droneId);
        int timeToCustomer = order.timeToFly(drone);

        drone.moveTime(timeToCustomer + 1);
        drone.setLocation(order);
        drone.deliver(productTypeId, itemsToDeliver);
        order.deliver(productTypeId, itemsToDeliver);
        actions.add(new DeliverAction(droneId, orderId, productTypeId, itemsToDeliver));
    }

    public void wait(int droneId, int time) {
        drones.get(droneId).moveTime(time);
        actions.add(new WaitAction(droneId, time));
    }

    public void load(int droneID, int wareHouseID, int itemType, int numberOfItem){
        Drone drone = drones.get(droneID);
        Warehouse warehouse = warehouses.get(wareHouseID);
        int timeToWarehouse = drone.timeToFly(warehouse);

        drone.moveTime(timeToWarehouse + 1);
        drone.setLocation(warehouse);
        drone.load(itemType, numberOfItem);
        warehouse.updateState(timeToWarehouse + 1, itemType, -numberOfItem);
        actions.add(new LoadAction(droneID, wareHouseID, itemType, numberOfItem));
    }
}
