package dao;

import java.sql.*;

public class MyConnection {
    String url="jdbc:oracle:thin:@localhost:1521:orcl";
    Connection con = null;
    public Connection getMyConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, "hr", "hr");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection con;
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        Statement stmt;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, "hr", "hr");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employees");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
