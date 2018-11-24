package 第2条_遇到多个构造器参数时要考虑用构建器;

import lombok.Setter;

/**
 * 繁杂的构造器对于多参数传入进行构造器来说是痛苦的，创建新对象也容易搞混淆
 * <1> 为此，第一种替代方案，即使用 JavaBeans 模式：在这种模式下，调用一个无参构造器来创建对象，然后调用 setter 方法来设置每个必要的
 *  参数，需要保证线程安全。
 * <2> Builder 模式：不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器，得到一个 builder 对象。
 *
 * 构建器 比 JavaBeans 更加安全，比传统的构造器可读性和编写性也更好。
 */

//JavaBeans Pattern
@Setter
class Example {
    private int servingSize  = -1;
    private int servings     = -1;
    private int calories     = 0;
    private int fat          = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;

    public Example(){}
}

// Builder Pattern
class Example1 {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories     = 0;
        private int fat          = 0;
        private int sodium       = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize,int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            this.calories = val;
            return  this;
        }
        public Builder fat(int val) {
            this.fat = fat;
            return this;
        }
        public Builder carbohydrate(int val) {
            this.carbohydrate = val;
            return this;
        }
        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public Example1 build() {
            return new Example1(this);
        }
    }

    private Example1(Builder builder) {
        this.servingSize   = builder.servingSize;
        this.calories      = builder.calories;
        this.carbohydrate  = builder.carbohydrate;
        this.fat           = builder.fat;
        this.sodium        = builder.sodium;
        this.servings      = builder.servings;
    }
}


public class Pra {
    public static void main(String[] args) {
        /**
         * 1. JavaBeans Pattern
         */
        Example example = new Example();
        example.setCalories(599);
        example.setCarbohydrate(33);
        example.setCarbohydrate(443);
        example.setFat(33);
        //... omitted

        /**
         *  2. Builder Pattern
         */
        Example1 example1 = new Example1.Builder(240, 8).calories(11).carbohydrate(11).
                carbohydrate(22).fat(222).sodium(22).build();
    }
}
