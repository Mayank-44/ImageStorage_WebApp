package com.nagarro.dao;

import java.io.InputStream;

import javax.servlet.http.Part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.service.SessionProvider;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;

import sun.misc.IOUtils;


/**
 * @author mayankgangwar
 * class to update the pre-existing image in DB
 */
public class UpdateImage {
	
	/**
	 * function to replace the newly updated image for given image id
	 * @param part contains the details of newly uploaded image
	 * @param user object of logged user
	 * @param id of selected image to be replaced
	 * @return updated user object
	 */
	public static User update(Part part,User user,int id)
	{
		Session session = SessionProvider.getSession();
		Transaction tns = session.beginTransaction();
		
		int usersize = user.getTotalFileSize();
		boolean update = false;
		try {
			
			Images im=session.get(Images.class, id);
			
			usersize += part.getSize() - im.getSize();
			if(usersize > 10485760)
			{
				System.out.println("Storage limit exceeded.");
				tns.commit();
			}
			else
			{
				update = true;
				im.setImageName(part.getSubmittedFileName());
				im.setSize((int)part.getSize());
				user.setTotalFileSize(usersize);
				InputStream is=null;
				
				is = part.getInputStream();
				
				im.setPreview(null);
				im.setPreview(IOUtils.readFully(is, -1, false));
				
				for(int i=0;i< user.getImg().size();i++) 
				{
					if(id==user.getImg().get(i).getId()) 
					{
						user.getImg().set(i, im);
					}
				}
				im.setUser(user);
				session.update(im);
				tns.commit();
				System.out.println("Updated "+part.getSubmittedFileName()+" to your account.");
			}
		}
		catch(Exception e) {
			System.out.println("user filesize limit exceeded.");
		}
		finally 
		{
			if(update == true)
			{
				tns = session.beginTransaction();
//				Query query = session.createQuery("UPDATE User u SET u.totalFileSize = :usersize WHERE u.id = :userId");
//				query.setParameter("usersize", usersize);
//				query.setParameter("userId", user.getUsername());
//				query.executeUpdate();
				session.merge(user);
				tns.commit();
			}
			System.out.println("Total Space Used: "+user.getTotalFileSize() + " bytes.\n");
			return user;
		}		
	}
}
