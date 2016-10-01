package chapter1_advanced_class_design.local_inner_classes;

/**
 * Local inner classes have the following properties:
 * ■ They do not have an access specifier.
 * ■ They cannot be declared static and cannot declare static fields or methods.
 * ■ They have access to all fields and methods of the enclosing class.
 * ■ They do not have access to local variables of a method unless those variables are final
 * or effectively final.
 *
 * @author advortco
 */
public class Main {
    private int length = 5;

    public void calculate() {
        final int width = 20;
        class Inner {
            public void multiply() {
                System.out.println(length * width);
            }
        }
        Inner inner = new Inner();
        inner.multiply();
    }

    public static void main(String[] args) {
        Main outer = new Main();
        outer.calculate();
    }
}
