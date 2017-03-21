package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection getConnection(){
		final String URL = "jdbc:mysql://localhost/demo";
		final String USER = "root";
		final String PASS  ="simplondev";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(URL, USER,PASS);
			return cn;
			
		}catch(Exception e){
			System.err.println("Erreur de connection a la db"+ e.getMessage());
			return null;
		}
	}
	public static void closeConnection(Connection cn){
		
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Echec cloture connection "+e.getSQLState());
		}
	}
}
