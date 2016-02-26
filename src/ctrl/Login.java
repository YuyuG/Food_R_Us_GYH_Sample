package ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("H");
		String cgiurl = "https://www.eecs.yorku.ca/~cse03116/auth/mw.cgi";
		String back = request.getRequestURL().toString();
		System.out.println(back);
		
		//System.out.print
		
		if(request.getParameter("user") == null)
		{
			System.out.println("yes");
			try{
			
			response.sendRedirect(cgiurl + "?back=" + back);
			
			}
			catch(Exception E )
			{
				System.out.println("cant redirect");
			}
			
			
		}
		else
		{
			
			
			response.sendRedirect("Index");
//			this.getServletContext().getRequestDispatcher("/" + "Index.jspx")
//			.forward(request, response);
			System.out.println("here?");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
