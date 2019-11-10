package com.nagarro.service;
import com.nagarro.dao.Authenticate;
import com.nagarro.tables.Images;
import com.nagarro.tables.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author mayankgangwar
 * class to manage the login process of the user
 */
@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	/**
	 * method accepting the credentials of the user and verifying them with the 
	 * information stored in DB
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		User user = Authenticate.authenticate(uname,password); // calling the static method to authenticate credentials
		
		if(user != null)
		{	
			session.setAttribute("uname", uname);
			List<Images> img=user.getImage();
			request.setAttribute("ImageList", img);
			request.setAttribute("totalsize", user.getTotalFileSize());
			System.out.println(user.getUsername()+" successfully Logged In.\n");
		}
		else
		{
			System.out.println("Invalid Credentials. Please try again!!!\n");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("Upload.jsp");
		rd.forward(request, response);
	}
}