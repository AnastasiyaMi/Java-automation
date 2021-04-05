import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public double result;
    public double num1;
    public double num2;
    public String operation;

    public Calculator(double num1, double num2, String operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
    }

    public void setNum1() {
        double num = 0;
        boolean isNum1Set = false;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter first number: ");
                num = input.nextDouble();
                isNum1Set = true;
            } catch (InputMismatchException inEx) {
                input.nextLine();
                System.out.println("Invalid input");
            }
        } while (!isNum1Set);
        this.num1 = num;
    }

    public void setNum2() {
        boolean isNum2Set = false;
        double num = 0;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter second number: ");
                num = input.nextDouble();
                isNum2Set = true;
            } catch (InputMismatchException inEx) {
                input.nextLine();
                System.out.println("Invalid input");
            }
        } while (!isNum2Set);
        this.num2 = num;
    }


    public void setOperation() {
        Scanner input = new Scanner(System.in);
        boolean isOperationValid = false;

        do {
            System.out.println("Enter the operation");
            operation = input.next();

            if (operation.equals("*") || operation.equals("/") || operation.equals("+") || operation.equals("-")) {
                isOperationValid = true;
            } else {
                input.nextLine();
                System.out.println("Invalid operation");
            }
        } while (!isOperationValid);

        this.operation = operation;
    }

    public double sum(double num1, double num2) {
        return num1 + num2;
    }

    public double minus(double num1, double num2) {
        return num1 - num2;
    }

    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public double division(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Division by zero is not possible");
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        } else {
            return num1 / num2;
        }
    }

    public double getResult() {

        switch (this.operation) {
            case "*":
                this.result = this.multiply(this.num1, this.num2);
                break;
            case "/":
                this.result = this.division(this.num1, this.num2);
                break;
            case "+":
                this.result = this.sum(this.num1, this.num2);
                break;
            case "-":
                this.result = this.minus(this.num1, this.num2);
                break;
            default:
                System.out.println("Invalid operation");
                break;
        }

        return this.result;

    }
}