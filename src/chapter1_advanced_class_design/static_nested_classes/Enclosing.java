package chapter1_advanced_class_design.static_nested_classes;

/**
 * In other words, it is like a regular class except for the following:
 ■ The nesting creates a namespace because the enclosing class name must be used to refer
 to it.
 ■ It can be made private or use one of the other access modifiers to encapsulate it.
 ■ The enclosing class can refer to the fields and methods of the static nested class.
 *
 * @author advortco
 */
public class Enclosing {
    static class Nested {
        private int price = 6;

        class InnerInStaticNested{
            private int in = 7;
        }

        void foo(){
            InnerInStaticNested innerInStaticNested = new InnerInStaticNested();
            System.out.println(innerInStaticNested.in);
        }
    }

    public static void main(String[] args) {
        Enclosing enclosing = new Enclosing();
        Nested nested = new Nested(); // doesn't need to create Enclosing-object
        Enclosing.Nested nested1 = new Enclosing.Nested(); // ok too

        System.out.println(nested.price);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
