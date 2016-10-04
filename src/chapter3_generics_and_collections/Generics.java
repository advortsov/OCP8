package chapter3_generics_and_collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aldvc
 * @date 04.10.2016.
 */
public class Generics {

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }


    public static void main(String[] args) {
        List<? super A> numList = new ArrayList<>();
        numList.add(new A());
        numList.add(new B());
        numList.add(new C());

        List<Number> numList1 = new ArrayList<Number>();

        List<? super Number> foo3 = new ArrayList<Object>(); // Object is a "super" of Number
        List<? super Integer> foo4 = new ArrayList<Number>(); // Object is a "super" of Number


        numList1.add(4);
        numList1.add(4.0);
        numList1.add(4f);
        sum(numList1);
    }

    public static Double sum(List<? extends Number> numList) {
        Double result = 0.0;
        for (Number num : numList) {
            result += num.doubleValue();
        }
        System.out.println(result);
        return result;
    }
}
