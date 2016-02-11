package hashcode.solution;

import hashcode.output.IAction;

import java.util.List;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class SimpleSolution implements ISolution {
    private Task task;

    public SimpleSolution(Task task) {
        List<Warehouse> warehouses = task.warehouses;
        for (Order order : task.orders) {
            Map<Integer, Integer> items = order.getOrderedItems();
            for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
                int type = entry.getKey();
                int value = entry.getValue();
                Warehouse w = getWireHouseWithItem(warehouses, type, value);
                if (w.itemsOfTypeLast(type) > value) {

                }
            }
            Warehouse warehouse = getWireHouseWithItem(warehouses, order.)
            for (Drone drone : task.drones) {


            }
        }
        this.task = task;
    }

    Warehouse getWireHouseWithItem(List<Warehouse> warehouses, int itemType, int needCount) {
        int max = -1;
        Warehouse id = null;
        for (Warehouse warehouse : warehouses) {
            //todo check that there is
            if (warehouse.itemsOfTypeLast(itemType) > max) {
                max = warehouse.itemsOfTypeLast(itemType);
                id = warehouse;
            }
            if (warehouse.itemsOfTypeLast(itemType) == needCount)
                return warehouse;
        }

        return id; //not full
    }

    @Override
    public List<IAction> solve() {
        return task.actions;
    }
}
