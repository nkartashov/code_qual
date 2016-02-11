package hashcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on
 * 11.02.16.
 */
public class Drone extends Located {
    private final Map<Integer, Integer> items = new HashMap<>(); // type, count

    public Drone(int row, int column) {
        super(row, column);
    }

    private int time;

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
