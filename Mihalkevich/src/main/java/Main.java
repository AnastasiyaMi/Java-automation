import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(5.5, 6, "+");
        DecimalFormat df = new DecimalFormat("###.####");
        calculator.setNum1();
        calculator.setNum2();
        calculator.setOperation();
        calculator.getResult();
        System.out.println(df.format(calculator.getResult()));
    }
}
