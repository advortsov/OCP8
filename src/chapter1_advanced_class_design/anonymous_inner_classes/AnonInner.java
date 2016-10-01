package chapter1_advanced_class_design.anonymous_inner_classes;

/**
 * This class
 *
 * @author advortco
 */

public class AnonInner {
    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            int dollarsOff() {
                return 3;
            }
        };
        return basePrice - sale.dollarsOff();
    }

    public static void main(String[] args) {
        AnonInner anonInner = new AnonInner();
        System.out.println(anonInner.admission(7)); // 4
    }
}
