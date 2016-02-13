package hashcode.solution;

import hashcode.output.IAction;

import java.util.*;

/**
 * Created by nikitakart on 11/02/16.
 */
public class SimpleSolution implements ISolution {
    private Task task;
    private Queue<Integer> droneQueue = new LinkedList<>();

    private void deliverItemWhere(Order order, int type, int count) throws DeadlineHit {
        while (count != 0) {
            Warehouse warehouse = getWarehouseWithItem(order, type, count);
            int maxStored = warehouse.itemsForType(type);
            int toDeliver = Math.min(maxStored, count);
            deliverGoodsFromWarehouse(order, warehouse, type, toDeliver);
            count -= toDeliver;
        }
    }

    private void deliverGoodsFromWarehouse(Order order, Warehouse warehouse, int type, int count) throws DeadlineHit {
        while (count != 0) {
            Drone drone = task.getClosest(warehouse);
            int maxCapacity = drone.maxOfType(type);
            int toDeliver = Math.min(maxCapacity, count);
            deliverGoodsFromWarehouseByDrone(order, warehouse, drone, type, toDeliver);
            count -= toDeliver;
        }
    }

    private void deliverGoodsFromWarehouseByDrone(Order order, Warehouse warehouse, Drone drone, int type, int count) throws DeadlineHit {
        task.load(drone, warehouse, type, count);
        task.deliver(drone, order, type, count);
    }

    private int getDroneId() {
        int droneId = droneQueue.element();
        droneQueue.remove();
        droneQueue.add(droneId);
        return droneId;
    }

    public SimpleSolution(Task task) {
        this.task = task;
        for (int droneId = 0; droneId < task.drones.size(); droneId++) {
            droneQueue.add(droneId);
        }
    }

    Warehouse getWarehouseWithItem(Order order, int type, int count) {
        int max = -1;
        Warehouse bestWarehouse = null;
        Collections.sort(task.warehouses, (o1, o2) -> o1.timeToFly(order) - o2.timeToFly(order));
        for (Warehouse warehouse: task.warehouses) {
            if (warehouse.itemsForType(type) > max) {
                max = warehouse.itemsForType(type);
                bestWarehouse = warehouse;
            }
            if (warehouse.itemsForType(type) >= count)
                return bestWarehouse;
        }

        return bestWarehouse; //not full
    }

    @Override
    public List<IAction> solve() {
        try {
            Collections.sort(task.orders, (o1, o2) -> {
                if (o1.totalItems == o2.totalItems) {
                    return (int) Math.round(o2.locality - o1.locality);
                } else {
                    return o1.totalItems - o2.totalItems;
                }
            });
            for (Order order: task.orders) {
                Map<Integer, Integer> items = order.getOrderedItems();
                for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                    deliverItemWhere(order, item.getKey(), item.getValue());
                }
            }
        } catch (DeadlineHit ex) {
            return task.actions;
        }
        return task.actions;
    }
}
