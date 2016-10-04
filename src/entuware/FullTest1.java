package entuware;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author aldvc
 * @date 02.10.2016.
 */
public class FullTest1 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        System.out.println(ls.stream().distinct().anyMatch(x -> x == 11));

        Path p1 = Paths.get("c:\\..\\temp\\test.txt");
        System.out.println(p1.normalize().toUri());
    }


}

