package hashcode.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by nikitakart on 11/02/16.
 */
public class ResultPrinter {
    private final String m_path;

    public ResultPrinter(String path) {
        m_path = path;
    }

    public void printResult(Object result) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(m_path)))) {
            writer.write(result.toString());
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
