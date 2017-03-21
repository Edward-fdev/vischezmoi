import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.UserDAO;
import model.User;
import util.Database;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
Connection db = Database.getConnection();
Statement st = db.createStatement();
ResultSet rs = st.executeQuery("select * from users");
System.out.println(rs.next());
	}

}
