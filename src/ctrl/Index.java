package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import model.ClientBean;
import model.Item;
import model.ItemBean;
import model.PO;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns =
{ "/Index", "/Startup", "/Startup/*" })
public class Index extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final Object po = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException
	{

		super.init();
		try
		{
			Item im = new Item();
			Cate cm = new Cate();
			CartCalc cc = new CartCalc();
			PO po = new PO();
			HashMap<String, Long> avgMap = new HashMap<String, Long>();
			this.getServletContext().setAttribute("itemModel", im);
			this.getServletContext().setAttribute("cateModel", cm);
			this.getServletContext().setAttribute("ccModel", cc);
			this.getServletContext().setAttribute("averageAddmap", avgMap);
			this.getServletContext().setAttribute("poModel", po);
			
			HashMap<String, Long> checkOutMap = new HashMap<String, Long>();
			this.getServletContext().setAttribute("checkOutmap", checkOutMap);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		this.getServletContext().setAttribute("maxP", "0");
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
		
		if(sn.getAttribute("freshVisit") == null)
		{
			sn.setAttribute("freshVisit", "freshVisit");
		}

		
//		if(request.getAttribute("clear") != null){
//			
//			request.setAttribute("cb", null);
//			request.setAttribute("ipbl", null);
//			request.setAttribute("cartCount", null);
//			request.setAttribute("cartItems", null);
//			sn.setAttribute("cb", null);
//			sn.setAttribute("ipbl", null);
//			sn.setAttribute("cartCount", null);
//			sn.setAttribute("cartItems", null);
//			
//		}

		ClientBean cusb = new ClientBean("accountgyh92", "customergyh92");
		request.setAttribute("customerInfo", cusb);
		sn.setAttribute("customerInfo", cusb);
		
		String jsp = "/Index.jspx";
		String ui = "/Index.jspx";

		Item im = (Item) this.getServletContext().getAttribute("itemModel");
		List<ItemBean> items;
		
		
		
		
		if (sn.getAttribute("cart") == null)
		{
			Map<String, Integer> cart = new HashMap<String, Integer>();
			sn.setAttribute("cart", cart);
		}

		if (sn.getAttribute("cartCount") != null)
		{
			request.setAttribute("cartCount", sn.getAttribute("cartCount"));
			System.out
					.println("Cartcount COUNT" + sn.getAttribute("cartCount"));
		}
		
		if (request.getParameter("searchtext") != null)
		{
			String text = request.getParameter("searchtext");
			request.getRequestDispatcher("Search?searchText="+ text).forward(request, response);

		}else{
		
		
		try
		{

			items = im.getItemName();
			request.getSession().setAttribute("allItemsInfo", items);

			String id = "";
			int catId= 0;
			
			
			
			
			{

				if (request.getParameter("viewCategory") == null)
				{

					// || (((request.getParameter("back") !=null)
					// &&(request.getParameter("back").toString().equals("return")))
					// && (request.getParameter("viewCategory") == null))

					System.out.println("is fresh");

					Cate cg = (Cate) this.getServletContext().getAttribute(
							"cateModel");
					List<CateBean> categorys;

					categorys = cg.retrieveCatrgory();
					// request.getSession().setAttribute("categorysInfo",
					// categorys);
					request.setAttribute("catelist", categorys);

					// jsp = ui;

				} else
				{
					String selectedCate = request.getParameter("viewCategory");
					String[] cateIDString = selectedCate.split(" ");
					int cateID = Integer.parseInt(cateIDString[2]);

					im = (Item) this.getServletContext().getAttribute(
							"itemModel");
					// List<ItemBean> items;

					// items =
					// im.getItemByCategory(Integer.parseInt(request.getParameter("category")));
					items = im.getItemByCategory(cateID);
					
					request.getSession().setAttribute("itemlist", items);
					request.setAttribute("itemlist", items);
					request.setAttribute("listlength", items.size());
					
				}
				jsp = ui;
			}
		} catch (Exception e)
		{
			jsp = ui;
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
		}
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
