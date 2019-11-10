package com.nagarro.createdb;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.dao.Hash;
import com.nagarro.service.SessionProvider;
import com.nagarro.tables.User;


/**
 * @author mayankgangwar
 * Class to populate the DB with dummy data to test.
 */
public class UserRegistration 
{
	public static Session session = SessionProvider.getSession();
	
    public static void main( String[] args )
    {
    	String psw="";
    	User user = null;

    	for(int i=1;i<=10;i++)
    	{
    		Transaction tns = session.beginTransaction();
    		
    		user = new User();
    		user.setUsername("Name_"+i);
    		psw = Hash.hash("password_"+i);
    		user.setPassword(psw);
    		user.setTotalFileSize(0);
    		session.save(user);
    		tns.commit();
    	}
    }
}

