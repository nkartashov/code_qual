package hashcode.output;

/**
 * Created by kate on
 * 11.02.16.
 */
abstract public class IDeliverAction implements IAction {
    final private int droneID;
    final private int wirehouseID;
    final private int productTypeID;

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public int getDroneID() {
        return droneID;
    }

    public int getWirehouseID() {
        return wirehouseID;
    }

    public int getProductTypeID() {
        return productTypeID;
    }

    final private int numberOfItem;

    public IDeliverAction(int droneID, int wirehouseID, int productTypeID, int numberOfItem) {
        this.droneID = droneID;
        this.wirehouseID = wirehouseID;
        this.productTypeID = productTypeID;
        this.numberOfItem = numberOfItem;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(droneID);
        builder.append(' ');
        builder.append(getCommand());
        builder.append(' ');
        builder.append(wirehouseID);
        builder.append(' ');
        builder.append(productTypeID);
        builder.append(' ');
        builder.append(numberOfItem);
        return builder.toString();
    }
}
