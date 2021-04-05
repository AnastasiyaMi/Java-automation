import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @BeforeAll
    static void startTesting() {
        System.out.println("Testing started");
    }

    @Test
    void getSumTest() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(5, 6, "+");
        assertEquals(4, calculator.sum(2, 2));
        assertEquals(5.44, calculator.sum(2.22, 3.22));
        assertEquals(-11.44, calculator.sum(-5.22, -6.22));
        assertEquals(1.999999998E9, calculator.sum(999999999, 999999999));
    }


    @Test
    void getSubTest() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(5, 6, "-");
        assertEquals(0, calculator.minus(2, 2));
        assertEquals(df.format(7.06), df.format(calculator.minus(10.28, 3.22)));
        assertEquals(1, calculator.minus(-5.22, -6.22));
        assertEquals(0, calculator.minus(999999999, 999999999));
    }

    @Test
    void getMulTest() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(5, 6, "*");
        assertEquals(4, calculator.multiply(2, 2));
        assertEquals(df.format(7.1484), df.format(calculator.multiply(2.22, 3.22)));
        assertEquals(df.format(7.4704), df.format(calculator.multiply(-2.32, -3.22)));
        assertEquals(9.99999998E17, calculator.multiply(999999999, 999999999));
    }

    @Test
    void getDivTest() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(5, 6, "/");
        assertEquals(1, calculator.division(2, 2));
        assertEquals(df.format(3.22), df.format(calculator.division(3.22, 1)));
        assertEquals(df.format(0.7205), df.format(calculator.division(-2.32, -3.22)));
        assertEquals(df.format(3.1739), df.format(calculator.division(10.22, 3.22)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.division(2, 0);
        });
    }


    @Test
    void getDivResult() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(6.6, 6, "/");
        assertEquals(df.format(1.1), df.format(calculator.getResult()));
    }

    @Test
    void getMulResult() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(2.22, 3.22, "*");
        assertEquals(df.format(7.1484), df.format(calculator.getResult()));
    }

    @Test
    void getSumResult() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(155, 3.22, "+");
        assertEquals(df.format(158.22), df.format(calculator.getResult()));
    }

    @Test
    void getSubResult() {
        DecimalFormat df = new DecimalFormat("###.####");
        Calculator calculator = new Calculator(155, 3, "-");
        assertEquals(df.format(152), df.format(calculator.getResult()));
    }

    @AfterAll
    static void endTesting() {
        System.out.println("Testing finished");
    }

}

