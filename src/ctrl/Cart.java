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

import model.CartBean;
import model.CartCalc;
import model.Cate;
import model.CateBean;
import model.Item;
import model.ItemBean;
import model.ItemPurchaseBean;


/**
 * Servlet implementation class Start
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private final double SHIPPING = 5;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart()
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

		
		
		Map<String, Integer> cart = (Map<String, Integer>) sn
				.getAttribute("cart");

		String jsp = "Index.jspx";
		String ui = "Index.jspx";
		


		Item im = (Item) this.getServletContext().getAttribute("itemModel");
		List<ItemBean> cartItems = new ArrayList<ItemBean>();

		for (Map.Entry<String, Integer> entry : cart.entrySet())
		{

			try
			{
				cartItems.add(im.getItemByID(entry.getKey()));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}

		CartCalc cc = (CartCalc) this.getServletContext().getAttribute(
				"ccModel");
		
		List<ItemPurchaseBean> ipbl;
		ipbl = cc.convertList(cartItems, cart);
		
		double subtotal = cc.calculateSub(ipbl);
		double hst = cc.calculateHST(subtotal);
		double grandtotal = cc.calcutlateGrandTotal(subtotal,hst);
		double shipping = SHIPPING;
		
		
		CartBean cb = new CartBean(ipbl, subtotal, shipping, hst, grandtotal);
		
		request.setAttribute("cb", cb);
		sn.setAttribute("cb", cb);
		
		
		
		if (request.getParameter("update") != null)
		{

			for (int i = 0; i < ipbl.size(); i++)
			{
				String itemId = ipbl.get(i).getIb().getNumber();
				String qtyUpdate = request.getParameter("qty" + itemId);
				try{
				cc.validateQty(qtyUpdate);
				

					if (qtyUpdate.equals("0"))
					{
						ipbl.remove(i);
						cartItems.remove(i);
						cart.remove(itemId);

					} else
					{
						System.out.println("qtyUpdate" + qtyUpdate);
						ipbl.get(i).setUnits(Integer.parseInt(qtyUpdate));
						ipbl.get(i).setExtendedPrice(
								cc.calculateExtendedPrice(ipbl.get(i)));
						cart.replace(itemId, Integer.parseInt(qtyUpdate)); 
					}
				
				}catch(Exception e){
					request.setAttribute("error", e.getMessage());
					request.setAttribute("whichError", i);
					sn.setAttribute("whichError", i);
					
				}
				
				System.out.println("BLLLLLAAAAAHHH" + ipbl.get(i).getIb().getName() + " " + ipbl.get(i).getUnits());
			}
			
			
			 
			cb = new CartBean(ipbl, subtotal, shipping, hst, grandtotal);
				request.setAttribute("cb", cb);
				sn.setAttribute("cb", cb);
			
			 subtotal = cc.calculateSub(ipbl);
			 hst = cc.calculateHST(subtotal);
			 grandtotal = cc.calcutlateGrandTotal(subtotal,hst);
			
			request.setAttribute("ipbl", ipbl);
			sn.setAttribute("ipbl", ipbl);

			request.setAttribute("cart", cart);
			sn.setAttribute("cart", cart);

			request.setAttribute("cartCount", cartItems.size());
			sn.setAttribute("cartCount", cartItems.size());
			
		}

		if(request.getParameter("cancel")!= null){
			cart.clear();
			ipbl.clear();
			cartItems.clear();
			
			request.setAttribute("ipbl", ipbl);
			sn.setAttribute("ipbl", ipbl);
			
			request.setAttribute("subtotal", "0.0");
			sn.setAttribute("subtotal", "0.0");
			request.setAttribute("hst", "0.0");
			sn.setAttribute("hst", "0.0");
			request.setAttribute("grandtotal", "0.0");
			sn.setAttribute("grandtotal", "0.0");
			
			request.setAttribute("cartCount", cartItems.size());
			sn.setAttribute("cartCount", cartItems.size());
			
			 cb = new CartBean(ipbl, 0.0, 0.0, 0.0, 0.0);

				request.setAttribute("cb", cb);
				sn.setAttribute("cb", cb);
			
			request.getRequestDispatcher("CheckOut").forward(request, response);
			
		}
		
		for (int i = 0; i < ipbl.size(); i++)
		{
			System.out.println("NEWWW BLLLLLAAAAAHHH" + ipbl.get(i).getIb().getName() + " " + ipbl.get(i).getUnits());
			
		}
		request.setAttribute("cb", cb);
		sn.setAttribute("cb", cb);
		
		request.setAttribute("ipbl", ipbl);
		sn.setAttribute("ipbl", ipbl);
		
		request.setAttribute("subtotal", subtotal);
		sn.setAttribute("subtotal", subtotal);
		request.setAttribute("hst", hst);
		sn.setAttribute("hst", hst);
		request.setAttribute("grandtotal", grandtotal);
		sn.setAttribute("grandtotal", grandtotal);
		
		request.setAttribute("cartCount", cartItems.size());
		sn.setAttribute("cartCount", cartItems.size());
		
		
		
		
		// response.sendRedirect("Index?viewCategory=View%20Category%20" +
		// catID);
		request.getRequestDispatcher("Cart.jspx").forward(request, response);
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
