package hashcode.solution;

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
}
