package hashcode;

import hashcode.input.InputReader;
import hashcode.output.ResultPrinter;
import hashcode.solution.ISolution;
import hashcode.solution.SimpleSolution;

public class Main {

    public static void main(String[] args) {
        for (String arg: args) {
            InputReader reader = new InputReader(arg);
            ISolution solution = new SimpleSolution(reader.readFile());
            ResultPrinter resultPrinter = new ResultPrinter(arg + ".out");
            resultPrinter.printResult(solution.solve());
        }
    }
}
