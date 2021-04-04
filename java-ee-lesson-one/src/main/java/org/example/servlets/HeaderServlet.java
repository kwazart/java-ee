package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/page_header")
public class HeaderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().println("<link rel='stylesheet' href='" + req.getContextPath() + "/style.css'>");

		resp.getWriter().println("<ul>");
		resp.getWriter().println("<li><a href='" + req.getContextPath() + "/first_http_servlet'>HTTP-SERVLET</a></li>");
		resp.getWriter().println("<li><a href='" + req.getContextPath() + "/first_servlet'>SERVLET</a></li>");
		resp.getWriter().println("<li><a href='#home'>HOME</a></li>");
		resp.getWriter().println("<li><a href='#about'>ABOUT</a></li>");
		resp.getWriter().println("</ul>");
	}
}
