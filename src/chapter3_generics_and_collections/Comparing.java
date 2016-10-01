package chapter3_generics_and_collections;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class
 *
 * @author advortco
 */
public class Comparing {
    static class Rabbit {
        public int id;

        public Rabbit(int id) {
            this.id = id;
            System.out.println("rabbit with id = " + this.id);
        }

        public Rabbit() {
        }

        @Override
        public String toString() {
            return "Rabbit{" +
                    "id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) {
        Comparator<Rabbit> idComparing = (r1, r2) -> r1.id - r2.id;
        Set<Rabbit> rabbits = new TreeSet<>(idComparing);
        rabbits.add(new Rabbit(2));
        rabbits.add(new Rabbit(1));
        rabbits.add(new Rabbit(3));
        rabbits.add(new Rabbit(4));

        System.out.println(rabbits);
    }
}
