package dataset4birttester;

import com.oaokolos.dataset4birt.Dataset;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Java4BIRTTester {
    public static void main(String[] args) throws SQLException {
        Dataset ds = null;
        try {
            // PostgreSQL
            ds = new Dataset("jdbc:postgresql://localhost:5432/test", "user", "123");
            // ADS
            //ds = new Dataset("jdbc:extendedsystems:advantage://srv-a10:6262/y$/fabius/reflis/dict.add", "AdsSys", "");
            //if (ds.select("select 1 as one from system.iota union all select 2 as one from system.iota")) {
            // PostgreSQL
            if (ds.select("select 1 as one union all select 2")) {
                while (ds.next()) {
                    System.out.println(Integer.toString(ds.field("one").asInteger()));
                }
            }
        } catch (Exception error) {
            System.out.println("ERROR!");
            Logger.getLogger(Dataset.class.getName()).log(Level.SEVERE, error.getMessage());
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }    
}
