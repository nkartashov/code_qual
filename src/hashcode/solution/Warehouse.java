package hashcode.solution;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Warehouse extends Located {
    private TreeMap<Integer, WarehouseState> states = new TreeMap<>();

    public Warehouse(int row, int column, List<Integer> itemsByType) {
        super(row, column);
        states.put(0, new WarehouseState(itemsByType));
    }

    public int itemsOfTypeLast(int type) {
        return states.lastEntry().getValue().getItemsByType().get(type);
    }

    public void updateState(int time, int type, int count) {
        WarehouseState lastState = states.lastEntry().getValue();
        List<Integer> copiedState = lastState.copy();
        copiedState.set(type, copiedState.get(type) + count);
        states.put(time, new WarehouseState(copiedState));
    }

    public TreeMap<Integer, WarehouseState> getStates() {
        return states;
    }
}
