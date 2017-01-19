import org.junit.runner.JUnitCore;
import tests.TestSuite;

public class MainClass {

    public static void main(String[] args) throws Exception {
        JUnitCore runner = new JUnitCore();
        runner.run(TestSuite.class);
    }
}
