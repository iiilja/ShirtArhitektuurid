/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Shirt;

/**
 *
 * @author ilja
 */
public class ShirtDAO {

    Connection con;
    Statement s;

    private void DB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String serverName = "imbi.ld.ttu.ee";
        String mydatabase = "t093817_shirt";
        String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
        String username = "t093817";
        String password = "94haTusj";
        /*String serverName = "hektor4.ttu.ee";
         String mydatabase = "t093817_shirt";
         String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
         String username = "t093817";
         String password = "NpxpIU38";*/

        try {
            con = DriverManager.getConnection(url, username, password);
            s = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Shirt[] findAll() throws ConnectException {
        DB();
        ArrayList<Shirt> shirtArray = new ArrayList<>();
        try {
            String sql = "SELECT * from shirt";
            if (s == null) {
                throw new ConnectException("could not connect to DB");
            }
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

    public Shirt findById(int id) throws ConnectException {
        DB();
        try {
            String sql = "SELECT * FROM shirt WHERE id='" + id + "'";
            if (s == null) {
                throw new ConnectException("could not connect to DB");
            }
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

    public void update(Shirt o) throws ConnectException {
        DB();
        String sql = "UPDATE shirt SET size='" + o.getSize() + "', cost='" + o.getCost() + "', description='" + o.getDescription() + "' WHERE id='" + o.getId() + "'";
        if (s == null) {
            throw new ConnectException("could not connect to DB");
        }
        try {
            s.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
