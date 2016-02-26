package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cate;
import model.CateBean;
import model.Item;
import model.ItemBean;


/**
 * Servlet implementation class Start
 */
@WebServlet("/Search")



public class Search extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search()
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
       
    	//Mortgage m = (Mortgage)this.getServletContext().getAttribute("model");
    	System.out.println("Do search");
		String search = request.getParameter("searchText");
		String searchBy = request.getParameter("searchBy");
		
		List<ItemBean> items = (List<ItemBean>) request.getSession().getAttribute("allItemsInfo");
		List<ItemBean> select = new ArrayList<ItemBean>();
		
		Item sh = (Item)this.getServletContext().getAttribute("itemModel");
		try
		{
			select = sh.getItemByName(search);
			
		}
		catch(Exception e)
		{
			
			request.setAttribute("exception", e);
			//throw new ServletException("something wrong with search",e);
		}
		
		request.getSession().setAttribute("searchResult",select);
		request.setAttribute("searchResult",select);
		
		
		this.getServletContext().getRequestDispatcher("/SearchResult.jspx")
		.forward(request, response);
		
        
        
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





