/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author ACER
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() 
        throws/* ClassNotFoundException */ NamingException, SQLException{
        
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("SE1614DS");
        Connection con = ds.getConnection();
        
        return con;
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://"
//                + "localhost:1433"
//                + ";databaseName=prj301_db"
//                + ";instanceName=NGANHA";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "1");
//        return con;
        
    }
    public static void getSiteMaps(ServletContext context) throws IOException {
        String filePath = context.getInitParameter("SITE_MAP_FILE_PATH");
        InputStream is = null;
        is = context.getResourceAsStream(filePath);
        Properties siteMaps = new Properties();
        siteMaps.load(is);
        context.setAttribute("SITEMAPS", siteMaps);
    }
}
