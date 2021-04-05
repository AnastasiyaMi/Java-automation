package collections;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.MyStack;

public class MyStackTest {

    private MyStack<Integer> testingStack;

    @BeforeMethod
    public void initStack() {
        testingStack = new MyStack<>();
    }

    @DataProvider(name = "params")
    public static Object[][] getParams() {
        return new Object[][] {
                {"20", "10"},
                {"15", "10"}
        };
    }

    @Test(dataProvider = "params")
    public void testInitialSize(String a, String b) {
        System.out.println(a+b);
        Assert.assertEquals(testingStack.size(), 0);
    }

    @Test(groups = "additionTests")
    public void testPush() {
        testingStack.push(6);
        Assert.assertEquals(testingStack.size(), 1);
    }
}
