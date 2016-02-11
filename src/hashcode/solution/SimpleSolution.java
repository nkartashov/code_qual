package hashcode.solution;

import hashcode.output.IAction;

import java.util.*;

/**
 * Created by nikitakart on 11/02/16.
 */
public class SimpleSolution implements ISolution {
    private Task task;
    private Queue<Integer> droneQueue = new LinkedList<>();

    private boolean deliverItemWhere(int orderId, int type, int count) {
        while (count != 0) {
            while (true) {
                int droneId = getDroneId();
                Drone drone = task.drones.get(droneId);
                int warehouseId = getNearestWarehiuse(type, count, drone);
                Warehouse warehouse = task.warehouses.get(warehouseId);


                int left = warehouse.itemsOfTypeLast(type);
                int maxItems = drone.maxOfType(type);
                if (maxItems > 0) {
                    int countToDeliver = Math.min(count, maxItems);
                    if (drone.getTime() >= task.deadline) {
                        return false;
                    }
                    task.load(droneId, warehouseId, type, countToDeliver);
                    if (drone.getTime() >= task.deadline) {
                        return false;
                    }
                    task.deliver(droneId, orderId, type, countToDeliver);
                    count -= countToDeliver;
                    left -= countToDeliver;
                    if (left == 0) {
                        break;
                    }
                }
            }
        }
        return true;
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
            if (warehouse.itemsOfTypeLast(type) > max) {
                max = warehouse.itemsOfTypeLast(type);
                maxWarehouseId = warehouseId;
            }
            if (warehouse.itemsOfTypeLast(type) == count)
                return warehouseId;
        }

        return maxWarehouseId; //not full
    }

    int getNearestWarehiuse(int type, int count, Drone drone) {
        int max = -1;
        int maxWarehouseId = -1;
        int dist = Integer.MAX_VALUE;
        Map<Integer, Integer> distMap = new HashMap<>();
        for (int warehouseId = 0; warehouseId < task.warehouses.size(); warehouseId++) {
            Warehouse warehouse = task.warehouses.get(warehouseId);
            int realDist = warehouse.timeToFly(drone);
            if (warehouse.itemsOfTypeLast(type) > max) {
                max = warehouse.itemsOfTypeLast(type);
                maxWarehouseId = warehouseId;
            }
            if (warehouse.itemsOfTypeLast(type) == count) {
                distMap.put(realDist, warehouseId);
            }
        }

        if (!distMap.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int result = 0;
            for (Map.Entry<Integer, Integer> entry : distMap.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (key < min) {
                    result = value;
                }
            }
            return result;
        }

        return maxWarehouseId; //not full
    }

    @Override
    public List<IAction> solve() {
        for (int orderId = 0; orderId < task.orders.size(); orderId++) {
            Order order = task.orders.get(orderId);
            Map<Integer, Integer> items = order.getOrderedItems();
            for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                if (!deliverItemWhere(orderId, item.getKey(), item.getValue())) {
                    return task.actions;
                }
            }
        }
        return task.actions;
    }
}
