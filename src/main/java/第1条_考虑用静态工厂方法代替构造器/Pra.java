package 第1条_考虑用静态工厂方法代替构造器;

import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Random;

/**
 * 静态工厂方法，对比与构造器，有如下优势
 *  1. 静态工厂方法方法有名称，避免构造单一命名的不清楚；
 *  2. 不必在每次调用它们的时候都创建一个新对象；
 *  3. 返回类型的原返回类型的任何子类型对象,增加灵活性；
 *  4. 在创建参数化类型实例的时候，它们使代码变得更加简洁。
 * 同时，存在以下缺点
 *  1. 类如果不含公有的或者受保护的构造器，就不能被子类化；
 *  2. 它们与其他的静态方法实际上没有任何区别。
 *
 *  一些惯用的名称
 *  | valueOf
 *  | of
 *  | getInstance
 *  | newInstance
 *  | getType
 *  | newType
 */


//实现了Hashmap 的静态工厂实现的例子
class MyHashMap extends HashMap {
    public static <K,V> HashMap<K, V> newInstance() {
        return new HashMap<K, V>();
    }
}

public class Pra {
    public static void main(String[] args) {
        Boolean flag = Boolean.FALSE;//比较常见的例子
        BigInteger bi = BigInteger.probablePrime(20, new Random());//BigInteger 静态工厂的使用例子
        System.out.println(bi);
        HashMap<Integer,String> g = MyHashMap.newInstance();
    }
}
