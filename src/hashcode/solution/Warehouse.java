package hashcode.solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Warehouse extends Located {
    private Map<Integer, WarehouseState> states = new HashMap<>();

    public Warehouse(int row, int column, List<Integer> itemsByType) {
        super(row, column);
        states.put(0, new WarehouseState(itemsByType));
    }

    public Map<Integer, WarehouseState> getStates() {
        return states;
    }
}
