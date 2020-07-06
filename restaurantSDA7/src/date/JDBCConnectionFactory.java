package date;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connect to Database
 */
public class JDBCConnectionFactory {
   
	
	public static final String URL = "jdbc:mysql://localhost:3306/restaurant3";
    public static final String USER = "root";
    public static final String PASS = "nustiuparola2017";
    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = JDBCConnectionFactory.getConnection();
        if (connection!=null) {
        	System.out.println("Am luat conexiune!");
        }
        else {
        	System.out.println("N-am conexiune!");
        }
    }
}