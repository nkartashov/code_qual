package hashcode.solution;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Order extends Located implements Comparable<Order> {
    private int totalItems;

    public Map<Integer, Integer> getOrderedItems() {
        return orderedItems;
    }

    private Map<Integer, Integer> orderedItems;
    private int delivered;

    private final double locality;

    public Order(int row, int column, int totalItems, Map<Integer, Integer> orderedItems) {
        super(row, column);
        this.totalItems = totalItems;
        this.orderedItems = orderedItems;
        int maxItems = 0;
        for (Integer items: orderedItems.values()) {
            maxItems = Math.max(maxItems, items);
        }
        locality = maxItems / totalItems;
    }

    public void deliver(int type, int count) {
        orderedItems.put(type, orderedItems.get(type) - count);
        delivered += count;
    }

    public boolean isFullFilled() {
        return delivered == totalItems;
    }

    @Override
    public int compareTo(Order o) {
        if (totalItems == o.totalItems) {
            if (locality > o.locality) {
                return 1;
            } else if (locality < o.locality) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (totalItems < o.totalItems) {
                return 1;
            } else if (totalItems < o.totalItems) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
