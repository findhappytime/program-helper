package fun.findhappytime.helper.test.override;

/**
 * @author ：zhangshu09
 * @date ：Created in 2020-05-15 19:17
 * @description：
 */
public class SubClass extends SuperClass
{
    @Override
    public void method1()
    {
        System.out.println("subclass method1");
        super.method1();
    }

    @Override
    public void method2()
    {
        System.out.println("subclass method2");
    }
}
