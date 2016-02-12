package hashcode.solution;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Located extends IdObject {
    protected int row;
    protected int column;

    public Located(int id, int row, int column) {
        super(id);
        this.row = row;
        this.column = column;
    }

    public double distance(Located other) {
        return Math.sqrt(Math.pow(row - other.row, 2) + Math.pow(column - other.column, 2));
    }

    public int timeToFly(Located other) {
        double dist = distance(other);
        return (int) Math.ceil(dist);
    }

    public void setLocation(Located location){
        row = location.row;
        column = location.column;
    }
}
