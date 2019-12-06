package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.tools.DeleteDbFiles;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		initDb((DataSource)context.getBean("dataSource"));

		UserDao dao = context.getBean("userDao", UserDao.class);

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
	
	private static void initDb(DataSource dataSource) throws ClassNotFoundException, SQLException {
		DeleteDbFiles.execute("~", "testdb", true); // drop db if exist 'testdb'
        Connection c = dataSource.getConnection();
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
