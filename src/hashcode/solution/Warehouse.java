package hashcode.solution;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Warehouse extends Located {
    private List<Integer> itemsByType;

    public Warehouse(int id, int row, int column, List<Integer> itemsByType) {
        super(id, row, column);
        this.itemsByType = itemsByType;
    }

    public int itemsForType(int type) {
        return itemsByType.get(type);
    }

    public void updateState(int type, int count) {
        Integer previousValue = itemsByType.get(type);
        assert previousValue + count >= 0;
        itemsByType.set(type, previousValue + count);
    }
}
