package hashcode.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class WarehouseState {
    private int atTime;
    private List<Integer> itemsByType;

    public int getAtTime() {
        return atTime;
    }

    public List<Integer> getItemsByType() {
        return itemsByType;
    }

    public WarehouseState(List<Integer> newItemsByType) {
        atTime = 0;
        itemsByType = newItemsByType;
    }

    public List<Integer> copy() {
        return new ArrayList<>(itemsByType);
    }
}
