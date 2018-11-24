package 第4条_通过私有构造器强化不可实例化的能力;

/**
 * 一些工具类 (utility class) 不希望被实例化，实例化对它来说没有任何的意义。default constructor  是 public、noparameter 的
 * 企图将类做成抽象类来强制该类不可实例化是 行不通 的，因为该类可以被子类化，而编写的子类是可以实例化的，还会带来一些误解。
 */
public class Pra {
    // Suppress default constructor for noninstantiability
    private Pra() {
        throw new AssertionError();
    }
    // omitted ...
}
