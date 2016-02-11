package hashcode.output;

/**
 * Created by kate on
 * 11.02.16.
 */
public class WaitAction implements IAction {
    public int getDroneID() {
        return droneID;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    final int droneID;

    public WaitAction(int droneID, int numberOfItem) {
        this.droneID = droneID;
        this.numberOfItem = numberOfItem;
    }

    final int numberOfItem;
    @Override
    public char getCommand() {
        return 'W';
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(droneID);
        builder.append(" ");
        builder.append(getCommand());
        builder.append(" ");
        builder.append(numberOfItem);
        return builder.toString();
    }
}
