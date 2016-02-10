package hashcode.input;

import java.io.*;

/**
 * Created by nikitakart on 11/02/16.
 */
public class InputReader {
    private final String m_path;

    public InputReader(String path) {
        m_path = path;
    }

    public Object readFile() {
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(m_path))))) {
            return inputStream.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }
}
