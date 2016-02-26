package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@WebServlet("/AddItem")



public class AddItem extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem()
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
    	
    	
    	  String jsp = "Index.jspx";
          String ui = "Index.jspx";
        
    	// TODO Auto-generated method stub
       
    	//Mortgage m = (Mortgage)this.getServletContext().getAttribute("model");
    	
    	HttpSession sn = request.getSession();
    	
    	

    	
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
		
		
		
		
    	Map<String, Integer> cart = (Map<String, Integer>) sn.getAttribute("cart");
    	
      
        String productID = request.getParameter("productID");
        if(productID == null){
        	System.out.println("NOOO CART MAP");
        }else{
        System.out.println("CREATE MAP");
        
        if(!cart.containsKey(productID)){
        	cart.put(productID, 1);
        }else{
        	cart.replace(productID, cart.get(productID)+1);
        }
        sn.setAttribute("itemAddedToCart", "itemAddedToCart");
        
        String catID = request.getParameter("catID");
       
        request.setAttribute("cartCount", cart.size());
       
        sn.setAttribute("cartCount", cart.size());
        
        
//        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
//           
//            if(entry.getKey().contentEquals(productID))
//            {
//            	entry.setValue(entry.getValue()+1);
//            }
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        
//        
//        }
        
        jsp = "Index?viewCategory=View%20Category%20"+ catID;
        }
        //response.sendRedirect("Index?viewCategory=View%20Category%20" + catID);
        request.getRequestDispatcher(jsp).forward(request, response);
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





