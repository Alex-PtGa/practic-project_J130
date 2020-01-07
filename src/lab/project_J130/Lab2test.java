
package lab.project_J130;

/**
 *
 * @author Alexandr Krasilnikov
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class Lab2test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (!loadDriver()) {
            System.out.println("Драйвер не загружается Ж :(");
            System.exit(1);
        } else {
            System.out.println("Опа, драйвер загружен!");
        }
        try (Connection conn = getConnection()) {
            System.out.println("Connection" + conn);
            printAllProducts(conn);
            Product pr = new Product(100, "Золото", 2000);
            pr.save(conn);
            // вызов метода и распечатка в цикле коллекции getAllProducts
           
// Что то делает соединение
        } catch (SQLException ex) {

        }
    }

    private static boolean loadDriver() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");               
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Не найден драйвер " + ex.getMessage());
            return false;
        }
    }

    private static Connection getConnection() {
        String uri = "jdbc:hsqldb:hsql://localhost:9002/example";
        try {
            Connection conn = DriverManager.getConnection(uri, "example", "");
            System.out.println("conn получен!");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Ошибка соединения!");
            return null;
        }
    }

    private static void printAllProducts(Connection conn) throws SQLException {
        try {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM PRODUCT";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                System.out.println("id = " + id
                        + ", name = " + name + ", price = " + price);
            }
        } catch (SQLException ex) {
        }
    }
    //    
    private static Collection<Product> getAllProducts(Connection conn) throws SQLException {

        try {
            Collection coll = new ArrayList();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM PRODUCT";
            ResultSet rs = st.executeQuery(sql);       // выборка данных возврат из базы 
            while (rs.next()) {
                int id = rs.getInt("id");              // 
                String name = rs.getString("name");    //
                double price = rs.getDouble("price");  //
                // создается обект Product 
                Product product = new Product(id, name, price);
                coll.add(product);
            }
            return coll;
        } catch (SQLException ex) {    }
        return null;       
    }    
}