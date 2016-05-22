import java.sql.*;

public class Main {

    public static void main(String args[]) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/";
        String db = "test";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "mittal";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db, user, pass);
            st = con.createStatement();

            String sql = "select * from login";
            rs = st.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int rowCount = metaData.getColumnCount();

            System.out.println("Table Name : " + 
            metaData.getTableName(rowCount));
            System.out.println("No of Field : " + rowCount);
            System.out.print("Field Name :");
            for (int i = 0; i < rowCount; i++) {
                System.out.print("  " + metaData.getColumnLabel(i+1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

