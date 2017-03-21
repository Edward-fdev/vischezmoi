package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.Database;

public class UserDAO {
	 
	private Connection connect;

	public UserDAO() {
		connect  = Database.getConnection();
	}
	
    public void checkUser(User user) {
        try {
            PreparedStatement ps = connect.prepareStatement("select pseudo from users where pseudo = ?");
            ps.setString(1, user.getPseudo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                updateUser(user);
            } else {
                addUser(user);
            }
        } catch (Exception e) {
            System.out.println("Error in check() -->" + e.getMessage());
        } 
    }
	
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("insert into users(pseudo, login, password, date_inscription) values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getPseudo());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());            
            preparedStatement.setDate(4, new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String userId) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("delete from users where pseudo=?");
            // Parameters start with 1
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("update users set password=?, login=?, date_inscription=?"
                    + "where pseudo=?");
            // Parameters start with 1
            System.out.println(new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setDate(3, new java.sql.Date(user.getDateInscription().getTime()));
            preparedStatement.setString(4, user.getPseudo());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setPseudo(rs.getString("pseudo"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                user.setDateInscription(rs.getDate("date_inscription"));
                users.add(user);
            }
        } catch (SQLException e) {
        	System.out.println("fuck!!");
            e.printStackTrace();
        }
 
        return users;
    }
 
    public User getUserById(String userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("select * from users where uname=?");
            preparedStatement.setString(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
 
            if (rs.next()) {
                user.setPseudo(rs.getString("pseudo"));
                user.setPassword(rs.getString("password"));
                user.setLogin(rs.getString("login"));
                user.setDateInscription(rs.getDate("date_inscription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return user;
    }
}
