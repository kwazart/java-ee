package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
	private List<String> list;

	@Override
	public void init() throws ServletException {
		list = new ArrayList<>();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<h3>Форма для добавления</h3>");
		resp.getWriter().println("<form method='post'>");
		resp.getWriter().println("Введите строку: <input type='text' name='line'>");
		resp.getWriter().println("<input type='submit'>");
		resp.getWriter().println("</form>");

		String line = req.getParameter("line");
		list.add(line);

		for (String s : list) {
			if (s != null) {
				resp.getWriter().println("<p>" + s + "</p>");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
