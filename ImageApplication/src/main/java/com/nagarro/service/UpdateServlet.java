package com.nagarro.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nagarro.dao.UpdateImage;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;


/**
 * @author mayankgangwar
 * Servlet to handle the image update request of the user
 */
@WebServlet("/UpdateServlet")
@MultipartConfig(maxFileSize = 1048576) // maximum file size for upload is 1 MB
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		if(uname == null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		
		User user = (User) SessionProvider.getSession().get(User.class,uname);
		try {
			Part part = request.getPart("file");
			int id = Integer.parseInt(request.getParameter("id"));
			if(ImageTypeValidate.validateType(part))
			{
				user = UpdateImage.update(part,user,id);
			}
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			System.out.println("Maximum File size exceeded.\n");
		}
		finally {
			List<Images> img = user.getImage();
			request.setAttribute("ImageList", img);
			request.setAttribute("totalsize", user.getTotalFileSize());
			RequestDispatcher rd=request.getRequestDispatcher("Upload.jsp");
			rd.forward(request, response);
		}
	}
}

