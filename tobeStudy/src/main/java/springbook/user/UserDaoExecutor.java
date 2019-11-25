package springbook.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.NConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoExecutor {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
