package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Car;
import model.CarForm;

public class CarDAO {
	Connection con;
	Statement s;
	public void DB(){
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			
		}

		String serverName = "imbi.ld.ttu.ee";
		String mydatabase = "t112528";
		String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;

		String username = "t112528";
		String password = "llj39y0G";
		try {
			con = DriverManager.getConnection(url, username, password);
			s = con.createStatement();
		} catch (SQLException e) {
			
		}
	}
	public Car[] findAll(){
		Car[] car = null;
		try{
		String sql = "SELECT count(*) from car";
		ResultSet rs = s.executeQuery(sql);
		int ids = 0;
		while(rs.next()){
		ids = rs.getInt("count");
		}
		car = new Car[ids];
		for(int i=1; i<=ids; i++){
		car[i-1]=(findById(i));
		}
		}catch (SQLException e) {

		}

		return car;
		}
	public Car findById(int id) {
		try{
			String sql = "SELECT * FROM car WHERE car='"+id+"'";
            ResultSet rs = s.executeQuery(sql);
            Car c = new Car();
            while (rs.next()) {
                String type = rs.getString("type");
                String desc = rs.getString("description");
                int count = rs.getInt("count");
                c.setId(id);
                c.setCount(count);
                c.setType(type);
                c.setDesc(desc);
            }
              
            return c; 
		}catch (SQLException e) {
			System.out.println(e);
			
		}
		
		return new Car();
	}
	public void update(CarForm o) {
		String sql = "UPDATE car SET type='"+o.getType()+"', count='"+o.getCount()+"', description='"+o.getDesc()+"' WHERE car='"+o.getId()+"'";
		try{
			s.executeQuery(sql);
		}catch(SQLException e){
			
		}
	}

}
