package 第3条_用私有构造器或者枚举类型强化Singleton属性;

/**
 * Singleton 指仅仅被实例化一次的类，表示那些本质上唯一的系统组件
 *  在 java 1.5 之前主要有两种方式，都要保证构造器是私有的，导出公有的静态成员，以便客户端能够访问该类唯一实例
 *  在 java 1.5 发行之后，只需编写一个包含单个元素的枚举类型就可以了，更加简洁，无偿提供序列化机制，绝对防止多次实例化，即使在面对复杂的
 * 反射攻击的时候。即使没有广泛采用，但是单元素的枚举类型已经成为实现 Singleton 的最佳方法。
 */

/**
 * 公有的 final 静态成员 变量,
 * 可以通过反射机制调用构造器，需要在创建第二个实例的时候抛出异常
 */
class Example1 {
    public static final Example1 INSTANCE = new Example1();
    private Example1() {}

    // omitted ...
}

/**
 * 公有的成员是一个静态工厂方法,能够清晰地表面这个类是一个 Singleton，提供了灵活性
 * 可序列化要求，反序列化
 * //readResolve method to preserve singleton property
 * private Object readResolve() {
 *     // Return the one true Object and let the garbage collector
 *     // tack care of the Object impersonator
 *     return INSTANCE;
 * }
 */
class Example2 {
    private static final Example2 INSTANCE = new Example2();
    private Example2() {}
    public static Example2 getInstance() {
        return INSTANCE;
    }

    // omitted ...
}

/**
 * 只需编写一个包含单个元素的枚举类型就好了
 */

enum Example3{
    INSTANCE;
}

public class Pra {

}
