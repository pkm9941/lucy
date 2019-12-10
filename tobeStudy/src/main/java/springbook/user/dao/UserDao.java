package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
		Connection c = dataSource.getConnection();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            stmt.execute("create schema if not exists lucy;");
            stmt.execute("create table if not exists lucy.users (id varchar(50) primary key, name varchar(50), password varchar(50));");
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
	
	public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();
        
		PreparedStatement ps = c.prepareStatement("insert into lucy.users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
	
		ps.executeUpdate();
	
		ps.close();
		c.close();
	}

	public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();
        
		PreparedStatement ps = c.prepareStatement("select * from lucy.users where id =?");
		ps.setString(1, id);
	
		ResultSet rs = ps.executeQuery();
		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
	
		rs.close();
		ps.close();
		c.close();
		
		if (user == null) throw new EmptyResultDataAccessException(1);
	
		return user;
	}
	
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select count(*) from lucy.users");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from lucy.users");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
}
