package com.nagarro.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.service.SessionProvider;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;


/**
 * @author mayankgangwar
 * class to delete the selected image.
 */
public class DeleteImage {
	/**
	 * static function to delete the image and update the user
	 * @param user object of logged user
	 * @param id of image to be deleted
	 * @param uname 
	 * @return
	 */
	public static void delete(User user,int id) {
		Session session = SessionProvider.getSession();
		Transaction tns = session.beginTransaction();
		boolean update = false;
		int usersize = user.getTotalFileSize();
		
		Images im = null;
		try 
		{
			im = session.get(Images.class, id);
			if (im == null) {
				System.out.println("no such image");
			}
			else
			{
				usersize = usersize - im.getSize();
				user.setTotalFileSize(usersize);
				
				session.merge(user);
				tns.commit();
				session.close();
				session = SessionProvider.getSession();
				tns = session.beginTransaction();
				session.delete(im);
				update = true;
			
				tns.commit();
				System.out.println("Image successfully deleted.");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while deleting the image.\n");
		}
	}
}
