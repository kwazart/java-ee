package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
"/something"                        /something

"/something/*"                      /something/first
									/something/second
									/something/../../..abc

"*.jpeg" / "*.html"                 /abc.jpeg

"hel*o.jpeg" / "*.html"                 /hello.jpeg    <----------- нельзя

""                                  /

"/"                                 будут обработаны все запросы (внешние)

"/*"                                будут обработаны все запросы (внешние + внутренние)
 */

@WebServlet("/first_http_servlet")
public class FirstHttpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		getServletContext().getRequestDispatcher("/page_header").include(req, resp);

		resp.getWriter().println("<h2>Вы внутри HTTP-сервлета!</h2>");
		resp.getWriter().println("<h2>You are inside the servlet!</h2>");

		resp.getWriter().println("<p>contextPath: " + req.getContextPath()+ "</p>");
		resp.getWriter().println("<p>servletPath: " + req.getServletPath()+ "</p>");
		resp.getWriter().println("<p>pathInfo: " + req.getPathInfo()+ "</p>");
		resp.getWriter().println("<p>queryString: " + req.getQueryString()+ "</p>");


		resp.getWriter().println("<p>param1: " + req.getParameter("name") + "</p>");
		resp.getWriter().println("<p>param2: " + req.getParameter("surname") + "</p>");
		resp.getWriter().println("<p>param3: " + req.getParameter("age") + "</p>");

		//resp.sendRedirect(req.getContextPath() + "/first_servlet");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
