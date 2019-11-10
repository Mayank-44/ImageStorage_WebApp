package com.nagarro.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.service.SessionProvider;
import com.nagarro.tables.*;

/**
 * @author mayankgangwar
 * class to authenticate the user
 */
public class Authenticate {
	
	/**
	 *  function to verify the details entered by user
	 * @param uname unique id of user
	 * @param password of user account
	 * @return object of user if exists else null
	 */
	public static User authenticate(String uname,String password)
	{
		Session session = SessionProvider.getSession();
		Transaction tns = session.beginTransaction();
		User user = new User();
		String pswHash = Hash.hash(password);
		user = session.get(User.class,uname);
		tns.commit();
		session.close();
		if(user == null)
			return null;
		else
		{
			if(user.getPassword().equals(pswHash))
			{
				return user;
			}
			else
				return null;
		}
	}

}
