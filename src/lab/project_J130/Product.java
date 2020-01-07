/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.project_J130;

/**
 *
 * @author Alexandr Krasilnikov
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alekra
 */
public class Product {

    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public void save(Connection conn) {
        try (Statement st = conn.createStatement()) {
            String sql = "SELECT * FROM PRODUCT WHERE ID=" + id + ";";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                //  иакой объект есть, нужен UPDATE
                System.out.println("Такой объект уже есть");
                sql = "UPDATE PRODUCT SET name'" + name + "' price = " + price + "WHERE id = " + id +";";
                int n = st.executeUpdate(sql);
                System.out.println("Изменено " + n + " строк");
            } else {
                //  Это новый объект для таблицы, нужен INSERT
                //   если есть строчный параметр, то переводит в строчный параметр строку
                sql = "INSERT INTO PRODUCT VALUES (" + id + "," + name + "'," + price + ");";
                int r = st.executeUpdate(sql);
                System.out.println("Вставлено " + r + "стр.");
            }

        } catch (SQLException ex) {
            System.out.println("Ошибка в save " + ex.getMessage());
        }
    }
}
