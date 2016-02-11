package hashcode.input;

import hashcode.solution.Order;
import hashcode.solution.Task;
import hashcode.solution.Warehouse;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nikitakart on 11/02/16.
 */
public class InputReader {
    private final String m_path;

    public InputReader(String path) {
        m_path = path;
    }

    public Task readFile() {
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(m_path))))) {
            String parameterLine = inputStream.readLine();
            String[] params = parameterLine.split(" ");
            int rows = Integer.parseInt(params[0]);
            int columns = Integer.parseInt(params[1]);
            int dronesCount = Integer.parseInt(params[2]);
            int deadline = Integer.parseInt(params[3]);
            int maxLoad = Integer.parseInt(params[4]);

            List<Integer> itemWeights = new ArrayList<>();
            int differentItems = Integer.parseInt(inputStream.readLine());
            String[] productWeights = inputStream.readLine().split(" ");
            for (int i = 0; i < differentItems; i++) {
                itemWeights.add(Integer.parseInt(productWeights[i]));
            }

            List<Warehouse> warehouses = new ArrayList<>();
            int warehouseCount = Integer.parseInt(inputStream.readLine());
            for (int i = 0; i < warehouseCount; i++) {
                String[] coords = inputStream.readLine().split(" ");
                List<Integer> itemsByType = new ArrayList<>();
                for (String items: inputStream.readLine().split(" ")) {
                    itemsByType.add(Integer.parseInt(items));
                }
                warehouses.add(new Warehouse(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), itemsByType));
            }

            List<Order> orders = new ArrayList<>();
            int orderCount = Integer.parseInt(inputStream.readLine());
            for (int i = 0; i < orderCount; i++) {
                String[] coords = inputStream.readLine().split(" ");
                int totalItems = Integer.parseInt(inputStream.readLine());
                Map<Integer, Integer> orderedItems = new HashMap<>();
                for (String item: inputStream.readLine().split(" ")) {
                    Integer iitem = Integer.parseInt(item);
                    orderedItems.put(iitem, orderedItems.getOrDefault(iitem, 0));
                }
                orders.add(new Order(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), totalItems, orderedItems));
            }
            return new Task(rows, columns, dronesCount, deadline, maxLoad, itemWeights, warehouses, orders);
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }
}
