package chapter6;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * @author aldvc
 * @date 25.09.2016.
 */
public class Test {

    public void read() throws SQLException {
        try {
            readFromDatabase();
        } catch (Exception e) { // Java 7 and later “translates” Exception in a catch block to the correct one.
            throw e;
        }
    }

    private void readFromDatabase() throws SQLException {
    }

}
