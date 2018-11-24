package 第7条_避免使用终结方法;

/**
 * Java语言规范并不保证finalize()会被及时执行，即不确定终结方法执行时间，只规定在对象被垃圾回收之前执行；
 * 不应该依赖终结方法来改变重要的持久状态；
 * 终结方法会造成严重的性能损耗。
 */
public class Pra {
    // 终结方法链不会自动执行，如果类有终结方法，并且子类覆盖了终结方法，则子类的终结方法必须手工调用超类的终结方法。
    // 应该在try块中终结子类，并在相应的finally块中调用超类的终结方法。
    @Override
    protected void finalize() throws Throwable {
        try {
            //ommited
        } finally {
            super.finalize(); //确保超类被终结
        }
    }

    // 如果子类实现者覆盖了超类的终结方法，但是"忘了"调用超类的终结方法，那么超类的终结方法永远不会调用。为了防止此种情况出现，
    // 可以使用终结方法守卫者。即为每个被终结的对象创建一个附加的对象，该附加对象为一个匿名类，将外围类的终结操作如释放资源
    // 放入该匿名类的终结方法中。同时，外围类保存着对该匿名类的唯一引用，即复制给私有变量域。
    //finalizer guardian
    private final Object finalizerGuardian = new Object() {
        @Override
        protected void finalize() throws Throwable {
            // pmmited
        }
    };
}


// DEMO
class A {

    @SuppressWarnings("unused")
    //终结守卫者
    private final Object finalizerGuardian = new Object() {
        @Override
        //终结守卫者的终结方法将被执行
        protected void finalize() {
            System.out.println("A finalize by the finalizerGuardian");
        }
    };


    @Override
    //由于终结方法被子类覆盖，该终结方法并不会被执行
    protected void finalize() {
        System.out.println("A finalize by the finalize method");
    }

}

class B extends A {

    @Override
    public void finalize() {
        System.out.println("B finalize by the finalize method");
    }


    public static void main(String[] args) throws Exception {
        B b = new B();
        b = null;
        System.gc();
        Thread.sleep(500);
    }
}