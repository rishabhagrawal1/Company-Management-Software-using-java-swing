package stock;

import java.sql.*;
import java.io.*;
/**
 *
 * @author rishabh
 */
public class ConnectionService {
    
    
    public static ResultSet select(String Query) throws Exception
    {
        try{
        Connection c=Connec.useCon();
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery(Query);
        return rs;}
         catch(Exception e)
        {
        try{
        FileOutputStream fos=new FileOutputStream("c:\\java\\Error_select.txt");
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
    public static int update(String Query) throws Exception
    {
        try{
        Connection c=Connec.useCon();
        Statement st=c.createStatement();
        int i=st.executeUpdate(Query);
       
        return i;
        }
         catch(Exception e)
        {
        try{
        FileOutputStream fos=new FileOutputStream("c:\\java\\Error_update.txt");
        PrintStream ps=new PrintStream(fos);
        e.printStackTrace(ps);
        return 0;
        }
        catch(Exception e1)
        {
            return 0;
        }
        
        }
    }
    
}
