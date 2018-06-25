package tcs.groupB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetDataController
 */
@WebServlet("/getData")
public class GetDataController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		MasterDao md = new MasterDao();
		ArrayList<Master> mrows = md.getMaster();
		
		/*for (Master M:mrows)
		{
		    System.out.println(M + "hello");
		    //out.println("<br/>");
		}
		*/
		
		request.setAttribute("MasterRow",mrows);//name, object
		
		/*Passing this object to jsp to display.
		 * we pass control to showTransaction.jsp using dispatcher*/
		
		RequestDispatcher rd = request.getRequestDispatcher("showTransaction.jsp");
		rd.forward(request, response);//forwarding request and response objects
		
	}

}
