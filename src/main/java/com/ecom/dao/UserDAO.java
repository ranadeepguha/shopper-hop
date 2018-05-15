package com.ecom.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ecom.exception.UserException;
import com.ecom.pojo.Email;
import com.ecom.pojo.User;


public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	

	
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personId= :personID");
			q.setInteger("personID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	
	public User delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + user.getUsername(), e);
		}
	}
	
	
	public List<User> getUsers() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> users = q.list();
			commit();
			return users;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get users ", e);
		}
	}

	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			//Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword(), u.getRole(),u.getEmail());
			System.out.println("First Name="+u.getFirstName());
			System.out.println("Last Name="+u.getLastName());
			System.out.println("Role="+u.getRole());
			//System.out.println("Email="+email);
			System.out.println("User="+user);
			
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setRole(u.getRole());
			
			user.setEmail(u.getEmail());
			//email.setUser(user);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	public int getUserWithSameEmail(String email) throws UserException
	{
		try {
			begin();
			Query q = getSession().createQuery("from User where email= :email");
			q.setString("email", email);		
			List<User> list=q.list();
			commit();
			return list.size();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + email, e);
		}
	}
	
	
	public int getUserWithSameUserName(String username) throws UserException
		{
			try {
				begin();
				Query q = getSession().createQuery("from User where username= :username");
				q.setString("username", username);		
				List<User> list=q.list();
				commit();
				return list.size();
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Could not get user " + username, e);
			}
		
	}

}
