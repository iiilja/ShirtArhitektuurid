package backend.DA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import frontend.control.LoginController;
import frontend.forms.LoginForm;
import general.Utils;
import log.MyLogger;
import backend.model.Employee;

public class SubjectsDAO {
	
	private String url, user, password;
	private Logger logger = Logger.getLogger(LoginController.class);
	
	public SubjectsDAO() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("DBConnection");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			Class.forName(bundle.getString("Driver"));
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO(): ", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Employee auth(LoginForm form) {
		logger.error("Dao saab sellise formi" + form.toString());
		form.setPassword(Utils.generateMD5(form.getPassword()));
		logger.error("Saadetav kasutajanimi" + form.getUsername() + " Saadetav parool" + form.getPassword());
		Connection db;
		ResultSet results;
		Employee employee = null;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeQuery("SELECT e.employee, e.person_fk,"
					+ "e.enterprise_fk, e.struct_unit_fk, e.active FROM"
					+ " employee E INNER JOIN user_account UA ON E.employee"
					+ "= UA.subject_fk WHERE UA.username='" + form.getUsername()
					+ "' AND UA.passw='" + form.getPassword() + "'");
			logger.error(results.toString());
			if (results.next()) {
				employee = new Employee(results.getLong("employee"),
						results.getLong("person_fk"),
						results.getLong("enterprise_fk"),
						results.getLong("struct_unit_fk"),
						results.getString("active"));
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.auth(): ", e.getMessage());
			e.printStackTrace();
		}
		return employee;
	}
	
	public String deletePerson(String id) {
		String answer = null;
		Connection db;
		ResultSet results;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeQuery("SELECT func_delete_person AS answer FROM func_delete_person("+id+");");
			
			if (results.next()) {
				
				answer = results.getString("answer");
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.deletePerson(): ", e.getMessage());
			e.printStackTrace();
		}
		
		return answer;
	}
	
	public String deleteEnterprise(String id) {
		String answer = null;
		Connection db;
		ResultSet results;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeQuery("SELECT func_delete_enterprise AS answer FROM func_delete_enterprise("+id+");");
			
			if (results.next()) {
				
				answer = results.getString("answer");
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.deleteEnterprise(): ", e.getMessage());
			e.printStackTrace();
		}
		
		return answer;
	}

	public String deleteContact(String id) {
		String answer = null;
		Connection db;
		int results = 0;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeUpdate("DELETE FROM contact WHERE contact="+id+";");
			
			MyLogger.logMessage("Deleted " + results + " contacts with id " + id);
			
			if (results > 0) {
				
				answer = "OK";
			}else {
				answer = "FAIL";
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.deleteEnterprise(): ", e.getMessage());
			e.printStackTrace();
		}
		
		return answer;
	}

	public String deleteAccount(String id) {
		String answer = null;
		Connection db;
		int results = 0;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeUpdate("DELETE FROM user_account WHERE user_account="+id+";");
			
			MyLogger.logMessage("Deleted " + results + " account with id " + id);
			
			if (results > 0) {
				
				answer = "OK";
			} else {
				answer = "FAIL";
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.deleteEnterprise(): ", e.getMessage());
			e.printStackTrace();
		}
		
		return answer;
	}

	public String deleteRole(String id) {
		String answer = null;
		Connection db;
		int results = 0;
		try {
			db = DriverManager.getConnection(url, user, password);
			Statement st = db.createStatement();
			results = st.executeUpdate("DELETE FROM employee_role WHERE employee_role="+id+";");
			
			MyLogger.logMessage("Deleted " + results + " role with id " + id);
			
			if (results > 0) {
				
				answer = "OK";
			} else {
				answer = "FAIL";
			}
			db.close();
		} catch(Exception e) {
			MyLogger.log("SubjectsDAO.deleteEnterprise(): ", e.getMessage());
			e.printStackTrace();
		}
		
		return answer;
	}
	
	
}