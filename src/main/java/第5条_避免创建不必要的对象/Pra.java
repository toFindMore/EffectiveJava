package 第5条_避免创建不必要的对象;

/**
 * 本节的精髓很简单，就是要注意对不可变对象的重用，尽量减少内存对空间开销。
 *  1.可以使用静态工厂方法；
 *  2.根据实际的运算场景来决定一个对象的复用是否值得，但有时候最求细枝末节的性能可能会使代码的可维护性降低，这也是不愿意看到的。
 */
public class Pra {
    public static void main(String[] args) {
        String s1 = new String("Hello"); // DON'T DO THIS
        String s2 = "Hello";                     // RECOMMAND

    }
}
