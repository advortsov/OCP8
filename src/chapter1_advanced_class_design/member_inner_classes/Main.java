package chapter1_advanced_class_design.member_inner_classes;

/**
 * A member inner class is defined at the member level of a class (the same level as the methods,
 instance variables, and constructors). Member inner classes have the following properties:
     ■ Can be declared public, private, or protected or use default access
     ■ Can extend any class and implement interfaces
     ■ Can be abstract or final
     ■ Cannot declare static fields or methods
     ■ Can access members of the outer class including private members
 * @author advortco
 */
public class Main {
    private int x = 10;

    class B {
        private int x = 20;

        class C {
            private int x = 30;

            public void allTheX() {
                System.out.println(x); // 30
                System.out.println(this.x); // 30
                System.out.println(B.this.x); // 20
                System.out.println(Main.this.x); // 10
            }
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        Main.B b = main.new B();
        Main.B.C c = b.new C();
        c.allTheX();
    }
}

