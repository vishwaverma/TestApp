package com.quytech.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBcH2Connection
{
	private static JDBcH2Connection instance = new JDBcH2Connection();
	private static final String DRIVER_CLASS = "org.h2.Driver";
	private static final String URL = "jdbc:h2:/data/data/" +
		    "com.quytech.testdb" +
		    "/data/hello" +
		    ";FILE_LOCK=FS" +
		    ";PAGE_SIZE=1024" +
		    ";CACHE_SIZE=8192";
	private static final String create_table		= "create table test(emp_id int primary key, emp_name varchar(255)"
     		+ "	, dob varchar(55), salary varchar(55), dept_id varchar(55))";
	 //private constructor
    private JDBcH2Connection() {
        try {
            //Step 2: Load H2 Java driver
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
 
    public static JDBcH2Connection getInstance() {
       if(instance == null) {
          instance = new JDBcH2Connection();
          createTable();
       }
       return instance;
    }
    private Connection createConnection() {
 
        Connection connection = null;
        try {
            //Step 3: Establish Java H2 connection
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
    
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /*log or print or ignore*/
            }
        }
    }
    private static void createTable()
    {
    	Connection con=getConnection();
    	try {
			Statement stat = con.createStatement();
			stat.execute(create_table);
			stat.close();
			close(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
/*    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log or print or ignore
            }
        }
    }
 
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log or print or ignore
            }
        }
    }
}
try {
    connection = ConnectionFactory.getConnection();
    statement = connection.createStatement();
    rs = statement.executeQuery(query);
} finally {
    DbUtil.close(rs);
    DbUtil.close(statement);
    DbUtil.close(connection);
}
*/
}
