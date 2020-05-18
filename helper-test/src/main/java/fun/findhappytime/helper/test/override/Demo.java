package fun.findhappytime.helper.test.override;

/**
 * @author ：zhangshu09
 * @date ：Created in 2020-05-15 19:17
 * @description：
 */
public class Demo {
    public static void main(String[] args)
    {
        SubClass mSubClass = new SubClass();
        mSubClass.method1();
    }



//    my expected output:
//
//    subclass method1
//    superclass method1
//    superclass method2
//
//    actual output:
//
//    subclass method1
//    superclass method1
//    subclass method2


   // The keyword super doesn't "stick". Every method call is handled individually, so even if you got to SuperClass.method1() by calling super, that doesn't influence any other method call that you might make in the future.

   // That means there is no direct way to call SuperClass.method2() from SuperClass.method1() without going though SubClass.method2() unless you're working with an actual instance of SuperClass.
}



