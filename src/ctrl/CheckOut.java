package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartCalc;
import model.Cate;
import model.CateBean;
import model.Item;
import model.ItemBean;
import model.ItemPurchaseBean;


/**
 * Servlet implementation class Start
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOut()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		// TODO Auto-generated method stub

		// Mortgage m =
		// (Mortgage)this.getServletContext().getAttribute("model");

		HttpSession sn = request.getSession();
		sn.setAttribute("checkout", "checkout");

		if(sn.getAttribute("cartCount") != null){
			request.setAttribute("cartCount", sn.getAttribute("cartCount"));
			
		}
		if(sn.getAttribute("subtotal") != null){
			request.setAttribute("subtotal", sn.getAttribute("subtotal"));
			
		}
		if(sn.getAttribute("hst") != null){
			request.setAttribute("hst", sn.getAttribute("hst"));
			
		}
		if(sn.getAttribute("grandtotal") != null){
			request.setAttribute("grandtotal", sn.getAttribute("grandtotal"));
			
		}
		if(sn.getAttribute("ipbl") != null){
			request.setAttribute("ipbl", sn.getAttribute("ipbl"));
			
		}
		if(sn.getAttribute("cb") != null){
			request.setAttribute("cb", sn.getAttribute("cb"));
			
		}
		
		
		

		// response.sendRedirect("Index?viewCategory=View%20Category%20" +
		// catID);
		request.getRequestDispatcher("Checkout.jspx").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);

	}

	protected void ipFilter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String ip = request.getRemoteAddr();

	}

}
