package chapter5_dates_strings_localization;

/**
 * This class
 *
 * @author advortco
 */
public class StringClassReview {
    public static void main(String[] args) {
//        Finally, here is an example that uses common String methods:
        String s = "abcde ";
        System.out.println(s.trim().length()); // 5
        System.out.println(s.charAt(4)); // e
        System.out.println(s.indexOf('e')); // 4
        System.out.println(s.indexOf("de")); // 3
        System.out.println(s.substring(2, 4).toUpperCase()); // CD
        System.out.println(s.replace('a', '1')); // 1bcde
        System.out.println(s.contains("DE")); // false
        System.out.println(s.startsWith("a")); // true

        System.out.println("=====================================");
        // StringBuilder
        StringBuilder b = new StringBuilder();
        b.append(12345).append('-');
        System.out.println(b.length()); // 6
        System.out.println(b.indexOf("-")); // 5
        System.out.println(b.charAt(2)); // 3
        StringBuilder b2 = b.reverse();  // 9 line
        System.out.println(b.toString()); // -54321 // 10 line
        System.out.println(b == b2); // true

        //On line 9, we reverse the StringBuilder and
//        return a reference to the same object. Line 10 prints this reversed value, and line 11 confirms that it is the same object.

        StringBuilder stringBuilder = new StringBuilder("abcde");
        stringBuilder.insert(1, '-').delete(3, 4);
        System.out.println(stringBuilder); //a-bde
        System.out.println(stringBuilder.substring(2, 4)); // bd
    }
}
