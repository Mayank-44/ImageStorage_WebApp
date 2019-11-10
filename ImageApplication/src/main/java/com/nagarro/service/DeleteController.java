package com.nagarro.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.dao.DeleteImage;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;

/**
 * @author mayankgangwar
 * Servlet to handle the delete request for an image
 */
@WebServlet("/deleteImage")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * method for handling the deletion of an image and updating the image and user object
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		if(uname == null)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		try
		{
			User user = (User) SessionProvider.getSession().get(User.class, uname);
			int id = Integer.parseInt(request.getParameter("id2"));
			DeleteImage.delete(user, id); // calling the static delete method to remove the selected image
			user = (User) SessionProvider.getSession().get(User.class, uname);
			System.out.println("Total Space Used: "+user.getTotalFileSize()+" bytes.\n");
			List<Images> img = user.getImage();
			request.setAttribute("ImageList", img);
			request.setAttribute("totalsize", user.getTotalFileSize());
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Something Wrong while deleting the image. Please try again!!!");
		}
		finally {
			// returning to the image display view
			RequestDispatcher rd=request.getRequestDispatcher("Upload.jsp");			
			rd.forward(request, response);
		}		
	}
}
