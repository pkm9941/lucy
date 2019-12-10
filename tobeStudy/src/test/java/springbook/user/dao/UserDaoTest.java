package springbook.user.dao;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		user1 = new User("gyume", "박성철", "springno1");
		user2 = new User("leegw700", "이길원", "springno2");
		user3 = new User("bumjin", "박범진", "springno3");
	}
	
	@Test
	public void addAndGet() throws SQLException {
		dao.deleteAll();
		int cntAfterDelete = dao.getCount();
		assertThat("삭제 후 데이터 수 0", 0, is(cntAfterDelete));
		
		User user = new User("lucy", "김연희", "married");

		dao.add(user);
		int cntAfterAdd = dao.getCount();
		assertThat("추가 후 데이터 수 1", cntAfterAdd, is(1));

		User user2 = dao.get(user.getId());
		assertThat("동일한 이름", user2.getName(), is(user.getName()));
		assertThat("동일한 아이디", user2.getId(), is(user.getId()));
		assertThat("동일한 비번", user2.getPassword(), is(user.getPassword()));
	}
	
	@Test
	public void getCount() throws SQLException {
		dao.deleteAll();
		assertThat("삭제 후 0건", dao.getCount(), is(0));
		dao.add(user1);
		assertThat("추가 후 1건", dao.getCount(), is(1));
		dao.add(user2);
		assertThat("추가 후 2건", dao.getCount(), is(2));
		dao.add(user3);
		assertThat("추가 후 3건", dao.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		dao.get("unknown_id");
	}
}
