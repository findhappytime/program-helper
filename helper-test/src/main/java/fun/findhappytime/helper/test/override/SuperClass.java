package fun.findhappytime.helper.test.override;

/**
 * @author ：zhangshu09
 * @date ：Created in 2020-05-15 19:16
 * @description：A
 */
public class SuperClass {
    public void method1()
    {
        System.out.println("superclass method1");
        this.method2();
    }

    public void method2()
    {
        System.out.println("superclass method2");
    }
}
