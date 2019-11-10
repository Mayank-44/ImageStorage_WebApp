package com.nagarro.service;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author mayankgangwar
 * Servlet to handle the logout action of the User
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session  = request.getSession();
		session.removeAttribute("uname");
		session.invalidate();
		System.out.println("Logged Out.\n");
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
