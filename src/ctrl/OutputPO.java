package ctrl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.ClientBean;
import model.PO;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/OutputPO")
public class OutputPO extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OutputPO()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
//		if (request.getSession().getAttribute("clientInfo") == null)
//		{
//			request.getSession().setAttribute("aboutToCheckout", "aboutToCheckout");
//			response.sendRedirect("/eFoods/Login");
//		} else
//		{
		String jsp;
		HttpSession sn = request.getSession();
		PO po = (PO) getServletContext().getAttribute(
				"poModel");
		Object cart = request.getSession().getAttribute("cb");
		
		//String id = request.getServletContext().getInitParameter("orderID");
		//ClientBean client = new ClientBean("user", "account");
		if(sn.getAttribute("cb") != null){
			request.setAttribute("cb", sn.getAttribute("cb"));
			
		}
		if(sn.getAttribute("customerInfo") != null){
			request.setAttribute("customerInfo", sn.getAttribute("customerInfo"));
			
		}
		CartBean cb = (CartBean) sn.getAttribute("cb");
		ClientBean client = (ClientBean) sn.getAttribute("customerInfo");
		
		try
		{
			String path = request.getSession().getServletContext().getRealPath("/PO");
			System.out.println(path);
			
			File dir = new File(path);
			String[] fileList = dir.list();
			System.out.println(fileList.toString());
			
			
			String fileName = po.genFileName(client, path);
			System.out.println("file name: " + fileName);
			
			
			Boolean flag = po.exportPO(cb, client, getServletContext()
					.getRealPath(fileName), fileName);
			System.out.println("success: " + flag);
//			request.setAttribute("fileName", fileName);
//			System.out.println(getServletContext()
//					.getRealPath(fileName));
//			request.getSession().setAttribute("export", flag);
			jsp = "/Confirmation.jspx";
		} catch (Exception e)
		{
			request.setAttribute("exception", e);
			e.printStackTrace();
			e.getMessage();
			jsp = "/exception.jspx"; // pass to Form with exception
		}
		
		request.removeAttribute("cb");
		request.removeAttribute("ipbl");
		request.removeAttribute("cartCount");
		request.removeAttribute("cartItems");
		request.removeAttribute("cart");
		sn.removeAttribute("cb");
		sn.removeAttribute("ipbl");
		sn.removeAttribute("cartCount");
		sn.removeAttribute("cartItems");
		sn.removeAttribute("cart");
		
		sn.setAttribute("clear", "clear");
		
		this.getServletContext().getRequestDispatcher("/Confirmation.jspx")
				.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
