package hashcode.solution;

import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Order extends Located {
    private int totalItems;

    public Map<Integer, Integer> getOrderedItems() {
        return orderedItems;
    }

    private Map<Integer, Integer> orderedItems;

    public Order(int row, int column, int totalItems, Map<Integer, Integer> orderedItems) {
        super(row, column);
        this.totalItems = totalItems;
        this.orderedItems = orderedItems;
    }
}
