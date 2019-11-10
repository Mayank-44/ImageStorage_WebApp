package com.nagarro.dao;

import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.nagarro.service.SessionProvider;
import com.nagarro.tables.*;


/**
 * @author mayankgangwar
 * class to save the newly uploaded image to DB
 */
public class SaveImage {

	/**
	 * function to save the image and update the User
	 * @param part contains the details of input image
	 * @param user logged user object
	 * @return updated user
	 */
	public static User save(Part part, User user) {
		Session session = SessionProvider.getSession();
		Transaction tns = session.beginTransaction();
		int usersize = user.getTotalFileSize();
		boolean update = false;
		
		try {
			usersize += part.getSize();
			if (usersize >= 10485760) {
				System.out.println("Storage limit exceeded.");
				tns.commit();
			} else {
				Images image = new Images();
				image.setImageName(part.getSubmittedFileName());
				image.setSize((int) part.getSize());
				InputStream is = null;
				user.setTotalFileSize(usersize);
				try {
					is = part.getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					image.setPreview(IOUtils.readFully(is, -1, false));
				} catch (IOException e) {
					e.printStackTrace();
				}
				user.getImg().add(image);
				image.setUser(user);
				session.save(image);
				tns.commit();
				update = true;
				System.out.println(part.getSubmittedFileName()+" uploaded to your account.");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			
				if (update == true) {
					
					tns = session.beginTransaction();
//					Query query = session.createQuery("UPDATE User u SET u.totalFileSize = :usersize WHERE u.id = :userId");
//					query.setParameter("usersize", usersize);
//					query.setParameter("userId", user.getUsername());
//					query.executeUpdate();
					session.merge(user);
					tns.commit();
					session.close();
				}
				System.out.println("Total Space Used: "+user.getTotalFileSize() + " bytes.\n");
				return user;
		}
	}
}
