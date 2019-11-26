package springbook.user.dao;

public class DaoFactory {
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMaker());
		return dao;
	}

	private DConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
