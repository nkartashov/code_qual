package hashcode.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nikitakart on 11/02/16.
 */
public class ResultPrinter {
    private final String m_path;

    public ResultPrinter(String path) {
        m_path = path;
    }

    public void printResult(List<IAction> actions) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(m_path)))) {
            StringBuilder builder = new StringBuilder();
            builder.append(actions.size());
            builder.append("\n");
            for (IAction c : actions) {
                builder.append(c.toString());
                builder.append("\n");
            }
            writer.write(builder.toString());
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
