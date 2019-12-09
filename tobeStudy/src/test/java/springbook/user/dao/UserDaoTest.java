package springbook.user.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.tools.DeleteDbFiles;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {

	static ApplicationContext context;
	
	@BeforeClass
	public static void beforeClass() throws BeansException, SQLException {
		context = new GenericXmlApplicationContext("applicationContext.xml");
		initDb((DataSource)context.getBean("dataSource"));
	}
	
	@Test
	public void addAndGet() throws SQLException {
		UserDao dao = context.getBean("userDao", UserDao.class);

		dao.deleteAll();
		int cntAfterDelete = dao.getCount();
		assertThat("삭제 후 데이터 수 0", 0, is(cntAfterDelete));
		
		User user = new User();
		user.setId("lucy");
		user.setName("김연희");
		user.setPassword("married");

		dao.add(user);
		int cntAfterAdd = dao.getCount();
		assertThat("추가 후 데이터 수 1", 1, is(cntAfterAdd));

		User user2 = new User();
		user2 = dao.get(user.getId());
		assertThat("동일한 이름", user2.getName(), is(user.getName()));
		assertThat("동일한 아이디", user2.getId(), is(user.getId()));
		assertThat("동일한 비번", user2.getPassword(), is(user.getPassword()));
	}
	
	private static void initDb(DataSource dataSource) throws SQLException {
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
