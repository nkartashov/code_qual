package hashcode.output;

/**
 * Created by kate on
 * 11.02.16.
 */
public class UnloadAction extends IDeliverAction {
    public UnloadAction(int droneID, int wirehouseID, int productTypeID, int numberOfItem) {
        super(droneID, wirehouseID, productTypeID, numberOfItem);
    }

    @Override
    public char getCommand() {
        return 'U';
    }
}
