package 第6条_消除过期的对象引用;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 对用完感觉以后不会再用的对象进行垃圾回收，来释放内存
 * 为此，消除过期对象对引用是很重要的,如果处理不好，会发生内存泄漏（memory leak）对危险
 */


// Can you spot the "memory leak" ?
class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {

    }

    public Object pop() {
        if(size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element,roughly
     * doubling the capacity each time the array needs to grow
     */
    private void ensureCapacity() {
        if(elements.length == size) {
            elements = Arrays.copyOf(elements,size*2+1);
        }
    }
}

/**
 * 对于上面对这段程序，存在内存泄漏对危险：随着内存占用对不断增加，程序性能会不断降低。甚至会导致 OutOfMemoryError 错误。
 * 内存泄漏主要体现在：如果这个栈先增长，然后再收缩，那么，从栈中弹出对对象将不会被当作垃圾回收，即使使用栈的程序不在引用这些对象，它们
 * 也不会被回收，存在 obsolete reference (过期引用)。
 * 故 pop 方法可以这么改正
 *
 * public Object pop() {
 *     if(size == 0) {
 *         throw new EmptyStackException();
 *     }
 *     Object result = element[--result];
 *     element[size] = null;
 *     return result;
 * }
 *
 *
 * 同时，memory leak 的另一个常见来源是缓存。缓存遗忘是一个很容易犯的错误 ，WeakHashMap 的使用 [TODO]
 * 利用后台线程（可能是 Timer 或者 ScheduledThreadPoolExecutor）来管理缓存。采用 LinkedHashMap 的 removeEldestEntry 可实现后一
 * 种方案。
 *
 * memory leak 第三个原因可能来自于 监听器 和 其他回调
 * 如果客户端在自己实现的API中注册回调，却没有显式地取消注册，那么除非自己采取某些动作，否则它们就会积聚。
 * 确保回调立即被当作垃圾回收的最佳方法是只保存它们的弱引用（weak reference），例如，只将它们保存为
 * WeakHashMap中的键。
 */
public class Pra {
}
