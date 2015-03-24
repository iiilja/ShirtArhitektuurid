/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.CarForm;
import model.Shirt;

/**
 *
 * @author ilja
 */
public class ShirtDAO {

    Connection con;
    Statement s;

    public void DB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        String serverName = "hektor4.ttu.ee";
        String mydatabase = "t093817_shirt";
        String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;

        String username = "t093817";
        String password = "NpxpIU38";
        try {
            con = DriverManager.getConnection(url, username, password);
            s = con.createStatement();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    public Shirt[] findAll() {
        ArrayList<Shirt> shirtArray = new ArrayList<>();
        try {
            String sql = "SELECT * from shirt";
            ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
                Shirt shirt = new Shirt(rs.getInt("id"), rs.getInt("cost"), 
                        rs.getString("size"), rs.getString("description"));
                shirtArray.add(shirt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shirtArray.toArray(new Shirt[shirtArray.size()]);
    }

    public Shirt findById(int id) {
        try {
            String sql = "SELECT * FROM shirt WHERE id='" + id + "'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Shirt shirt = new Shirt(rs.getInt("id"), rs.getInt("cost"), 
                        rs.getString("size"), rs.getString("description"));
                return shirt;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    public void update(Shirt o) {
        String sql = "UPDATE shirt SET size='" + o.getSize() + "', cost='" + o.getCost()+ "', description='" + o.getDescription()+ "' WHERE id='" + o.getId() + "'";
        try {
            s.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
