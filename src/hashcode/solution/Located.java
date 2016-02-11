package hashcode.solution;

import hashcode.Main;

/**
 * Created by nikitakart on 11/02/16.
 */
public class Located {
    protected int m_row;

    public int getM_column() {
        return m_column;
    }

    public int getM_row() {
        return m_row;
    }

    protected int m_column;
    public Located(int row, int column) {
        m_row = row;
        m_column = column;
    }

    public double distance(Located other) {
        return Math.sqrt(Math.pow(m_row - other.m_row, 2) + Math.pow(m_column - other.m_column, 2));
    }

    public int timeToFly(Located other) {
        double dist = distance(other);
        return (int) Math.ceil(dist);
    }
}
