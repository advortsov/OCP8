package chapter5_dates_strings_localization;

import java.util.ListResourceBundle;

/**
 * Creating a Java Class Resource Bundle
 *
 * There are two main advantages of using a Java class instead of a property file for a
 resource bundle:
 ■ You can use a value type that is not a String.
 ■ You can create the values of the properties at runtime.

 * @author advortco
 */
public class Zoo_en extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello", "Hello"},
                {"open", "The zoo is open"}};
    }
}
