package com.nagarro.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet to handle the newly updated image
 */
@WebServlet("/editImage")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * method to process the data sent by Upload view and redirect to the view 
	 * which allow user to upload the new image to be replaced for given image id
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			HttpSession session = request.getSession();
			if((String)session.getAttribute("uname") == null)
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
			int imageId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("ImageId", imageId);
			RequestDispatcher rd=request.getRequestDispatcher("EditForm.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Something went wrong while updating image. Please try again!!!.");
			request.getRequestDispatcher("Upload.jsp").forward(request, response);
		}
	}

}
