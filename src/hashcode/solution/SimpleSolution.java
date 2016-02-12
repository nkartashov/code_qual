package hashcode.solution;

import hashcode.output.IAction;

import java.util.*;

/**
 * Created by nikitakart on 11/02/16.
 */
public class SimpleSolution implements ISolution {
    private Task task;
    private Queue<Integer> droneQueue = new LinkedList<>();

    private void deliverItemWhere(int orderId, int type, int count) throws DeadlineHit {
        while (count != 0) {
            int warehouseId = getWarehouseWithItem(type, count);
            Warehouse warehouse = task.warehouses.get(warehouseId);
            int maxStored = warehouse.itemsForType(type);
            int toDeliver = Math.min(maxStored, count);
            deliverGoodsFromWarehouse(orderId, warehouseId, type, toDeliver);
            count -= toDeliver;
        }
    }

    private void deliverGoodsFromWarehouse(int orderId, int warehouseId, int type, int count) throws DeadlineHit {
        int droneId = getDroneId();
        while (count != 0) {
            Drone drone = task.drones.get(droneId);
            int maxCapacity = drone.maxOfType(type);
            int toDeliver = Math.min(maxCapacity, count);
            deliverGoodsFromWarehouseByDrone(orderId, warehouseId, droneId, type, toDeliver);
            count -= toDeliver;
        }
    }

    private void deliverGoodsFromWarehouseByDrone(int orderId, int warehouseId, int droneId, int type, int count) throws DeadlineHit {
        task.load(droneId, warehouseId, type, count);
        task.deliver(droneId, orderId, type, count);
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

    int getWarehouseWithItem(int type, int count) {
        int max = -1;
        int maxWarehouseId = -1;
        for (int warehouseId = 0; warehouseId < task.warehouses.size(); warehouseId++) {
            Warehouse warehouse = task.warehouses.get(warehouseId);
            if (warehouse.itemsForType(type) > max) {
                max = warehouse.itemsForType(type);
                maxWarehouseId = warehouseId;
            }
            if (warehouse.itemsForType(type) >= count)
                return warehouseId;
        }

        return maxWarehouseId; //not full
    }

    @Override
    public List<IAction> solve() {
        try {
            Collections.sort(task.orders);
            for (Order order: task.orders) {
                Map<Integer, Integer> items = order.getOrderedItems();
                for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                    deliverItemWhere(order.id, item.getKey(), item.getValue());
                }
            }
        } catch (DeadlineHit ex) {
            return task.actions;
        }
        return task.actions;
    }
}
