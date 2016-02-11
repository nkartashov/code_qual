package hashcode.output;

/**
 * Created by kate on
 * 11.02.16.
 */
public class DeliverAction implements IAction {
    final int droneID;
    final int customerID;
    final int productTypeID;
    final int numberOfItem;

    public int getDroneID() {
        return droneID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getProductTypeID() {
        return productTypeID;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public DeliverAction(int droneID, int customerID, int productTypeID, int numberOfItem) {
        this.droneID = droneID;
        this.customerID = customerID;
        this.productTypeID = productTypeID;
        this.numberOfItem = numberOfItem;
    }

    @Override
    public char getCommand() {
        return 'D';
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(droneID);
        builder.append(" ");
        builder.append(getCommand());
        builder.append(" ");
        builder.append(customerID);
        builder.append(" ");
        builder.append(productTypeID);
        builder.append(" ");
        builder.append(numberOfItem);

        return builder.toString();
    }
}
