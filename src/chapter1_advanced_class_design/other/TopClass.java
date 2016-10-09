package chapter1_advanced_class_design.other;

/**
 * The line public void doA() { inner1.doA(); } will cause compilation to fail because even though the
 * anonymous subclass of Inner has doA() method, Inner does not and the type of inner1 is Inner.
 * Therefore, compiler will complain that Inner does not have doA(). Observe that there is no way
 * doA() can be accessed.
 */
public class TopClass {
    public Inner inner1 = new Inner() {
        public void doA() {
            System.out.println("doing A");
        }
    };

    public void doA() {
//        inner1.doA();
    }
}

class Inner {
    int value;
}