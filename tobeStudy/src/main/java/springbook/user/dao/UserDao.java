package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

import springbook.user.domain.User;

public class UserDao {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		initDb();
		
		UserDao dao = new UserDao();

		User user = new User();
		user.setId("lucy");
		user.setName("김연희");
		user.setPassword("married");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");
	
		User user2 = new User();
		user2 = dao.get(user.getId());
		System.out.println(user2.getId() + " 조회 성공");
		
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
	
		ps.executeUpdate();
	
		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
        
		PreparedStatement ps = c.prepareStatement("select * from users where id =?");
		ps.setString(1, id);
	
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
	
		rs.close();
		ps.close();
		c.close();
	
		return user;
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection c = DriverManager.getConnection("jdbc:h2:~/testdb", "lucy", "1234");
		return c;
	}

	private static void initDb() throws ClassNotFoundException, SQLException {
		DeleteDbFiles.execute("~", "testdb", true); // drop db if exist 'testdb'
        Class.forName("org.h2.Driver");
        Connection c = DriverManager.getConnection("jdbc:h2:~/testdb", "lucy", "1234");
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            stmt.execute("create table users (id varchar(50) primary key, name varchar(50), password varchar(50));");
            stmt.close();
            c.commit();
            
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
