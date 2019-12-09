package springbook.user.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

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
}
