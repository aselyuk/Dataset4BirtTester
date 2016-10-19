package dataset4birttester;

import com.oaokolos.dataset4birt.Dataset;
import com.oaokolos.dataset4birt.Log;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Java4BIRTTester {
    public static void main(String[] args) throws SQLException {
        Dataset ds = null;
		Log log = null;
        try {
			log = new Log("c:\\temp\\test.log", Log.LOG_LEVEL_ALL);
            // PostgreSQL
            //ds = new Dataset("jdbc:postgresql://localhost:5432/test", "user", "123", "org.postgresql.Driver");
            //if (ds.select("select 1 as one union all select 2")) {
            // ADS
            ds = new Dataset("jdbc:extendedsystems:advantage://srv-a10:6262/y$/fabius/reflis/dict.add", "AdsSys", "");
			ds.setLog(log);
			if (ds.connect()) {
				if (ds.select("select 1 as one from system.iota union all select 2 as one from system.iota")) {
					while (ds.next()) {
						log.info(Integer.toString(ds.field("one").asInteger()));
					}
				}
			} else {
				log.error("Can't connect");
			}
        } catch (Exception error) {
            System.out.println("ERROR!" + error.getMessage());
            Logger.getLogger(Dataset.class.getName()).log(Level.SEVERE, error.getMessage());
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }    
}
