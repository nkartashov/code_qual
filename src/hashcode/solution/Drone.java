package hashcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on
 * 11.02.16.
 */
public class Drone {
    private final Located located;
    private final Map<Integer, Integer> items = new HashMap<>(); // type, count

    public Drone(Located located) {
        this.located = located;

    }

    private int time;

    public Located getLocated() {
        return located;
    }

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
