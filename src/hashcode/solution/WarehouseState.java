package hashcode.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class WarehouseState {
    public int getAtTime() {
        return atTime;
    }

    private int atTime;

    public List<Integer> getItemsByType() {
        return itemsByType;
    }

    private List<Integer> itemsByType;

    public WarehouseState(List<Integer> newItemsByType) {
        atTime = 0;
        itemsByType = newItemsByType;
    }
}
