package stock;
import java.io.*;
import java.sql.Connection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author rishabh
 */
public class Connec {
    /** Creates a new instance of Connection */
    public static Connection useCon() 
    {
        try{
        FileInputStream fis=new FileInputStream("C://java//connection1.txt");
      
        Properties p=new Properties();
        p.load(fis);
        fis.close();
        Class.forName(p.getProperty("Driver"));
        Connection con=DriverManager.getConnection(p.getProperty("Url"),p.getProperty("User"),p.getProperty("Password")); 
           return con;
        }
        catch(Exception e)
        {
        try{
        FileOutputStream fos=new FileOutputStream("c:\\java\\Error.txt");
        PrintStream ps=new PrintStream(fos);
        e.printStackTrace(ps);
        return null;
        }
        catch(Exception e1)
        {
            return null;
        }
        
        }
    }
    
}
