package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.*;
import examples.pubhub.model.*;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/BookReturn")
public class BookReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		String tag = request.getParameter("searchTag");
		
		TagDAO tagDao = DAOUtilities.getTagDAO();
		List<Book> bookList = tagDao.getBooksByTag(tag);
		
		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
		
	}
}
