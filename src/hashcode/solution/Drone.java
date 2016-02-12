package hashcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kate on
 * 11.02.16.
 */
public class Drone extends Located {
    private final Map<Integer, Integer> items = new HashMap<>(); // type, count
    private int currentLoad;
    private Task task;

    public Drone(int id, int row, int column, Task task) {
        super(id, row, column);
        currentLoad = 0;
        this.task = task;
    }

    private int time;

    public void moveTime(int time) throws DeadlineHit {
        this.time += time;
        checkDeadline(this.time);
    }

    public void checkDeadline(int time) throws DeadlineHit {
        if (time > task.deadline) {
            throw new DeadlineHit();
        }
    }

    public void deliver(int type, int count) {
        items.put(type, items.get(type) - count);
        currentLoad -= count * task.itemWeights.get(type);
    }

    public void load(int type, int count) {
        items.put(type, items.getOrDefault(type, 0) + count);
        currentLoad += count * task.itemWeights.get(type);
    }

    public int maxOfType(int type) {
        return (int) Math.floor((task.maxLoad - currentLoad) / task.itemWeights.get(type));
    }
}
