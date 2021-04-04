package org.example.servlets;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstServlet implements Servlet {

	private transient ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;

	}

	@Override
	public ServletConfig getServletConfig() {
		return config;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		getServletConfig().getServletContext().getRequestDispatcher("/page_header").include(request, response);

		response.getWriter().println("<h1>You are inside the servlet!</h1>");
		response.getWriter().println("<h2>You are inside the servlet!</h2>");
		response.getWriter().println("<h3>You are inside the servlet!</h3>");
		response.getWriter().println("<h4>You are inside the servlet!</h4>");
	}

	@Override
	public String getServletInfo() {
		return "First servlet";
	}

	@Override
	public void destroy() {

	}
}
