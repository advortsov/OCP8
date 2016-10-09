package chapter7_concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 *
 * Note that if you remove the line //1, //2, //3, and //4, (i.e. if you don't use any locking), you will see a ConcurrentModificationException.
 *
 * @author aldvc
 * @date 08.10.2016.
 */
public class ReentrantReadWriteLock_Student {
    private Map<String, Integer> marksObtained = new HashMap<String, Integer>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void setMarksInSubject(String subject, Integer marks) {
        lock.writeLock().lock(); //1
        try {
            marksObtained.put(subject, marks);
        } finally {
            lock.writeLock().unlock(); //2
        }
    }

    public double getAverageMarks() {
        lock.readLock().lock(); //3
        double sum = 0.0;
        try {
            for (Integer mark : marksObtained.values()) {
                sum = sum + mark;
            }
            return sum / marksObtained.size();
        } finally {
            lock.readLock().unlock();//4
        }
    }

    public static void main(String[] args) {

        final ReentrantReadWriteLock_Student s = new ReentrantReadWriteLock_Student();

        //create one thread that keeps adding marks
        new Thread() {
            public void run() {
                int x = 0;
                while (true) {
                    int m = (int) (Math.random() * 100);
                    System.out.println("Mark has been added!");
                    s.setMarksInSubject("Sub " + x, m);
                    x++;
                }
            }
        }.start();

        //create 5 threads that get average marks
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        double av = s.getAverageMarks();
                        System.out.println(av);
                    }
                }
            }.start();
        }
    }

}