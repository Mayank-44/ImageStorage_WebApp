package com.nagarro.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nagarro.dao.SaveImage;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;


/**
 * @author mayankgangwar
 * Servlet to handle the image upload request of the user
 */
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1048576)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if((String)session.getAttribute("uname") == null)
		{
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		List<Images> img = null;
		int totalsize = 0;
		try {
			User u = (User) SessionProvider.getSession().get(User.class, (String) session.getAttribute("uname"));
			img = u.getImg();
			totalsize = u.getTotalFileSize();
			Part part = request.getPart("file");
			
			if (ImageTypeValidate.validateType(part)) {
				u = SaveImage.save(part, u); // calling the static save method to save the new image in DB
				img = u.getImage();
				totalsize = u.getTotalFileSize();
			} 
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Maximum File size exceeded.\n");
		} finally {
			request.setAttribute("ImageList", img);
			request.setAttribute("totalsize", totalsize);
			RequestDispatcher rd = request.getRequestDispatcher("Upload.jsp");
			rd.forward(request, response);
		}
	}
}
