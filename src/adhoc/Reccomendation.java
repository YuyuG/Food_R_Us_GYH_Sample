package adhoc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.ItemBean;

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/Reccommend" }, servletNames = { "ctrl.AddItem" })
public class Reccomendation implements Filter {
	
	private static final String recommended = "2002H712";
	private static final String addedItem = "1409S413";
	
    /**
     * Default constructor. 
     */
    public Reccomendation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		
		if(request.getParameter("productID") != null){
			System.out.println("PIDDDD");
			
			HttpSession session = request.getSession();
			
			String itemID = request.getParameter("productID");//.split(";")[0];
			if(itemID.compareToIgnoreCase(addedItem) == 0){
				Item model = (Item) request.getServletContext().getAttribute("itemModel");
				try {
					//System.out.println(recommended);
					ItemBean recco = model.getItemByID(recommended);
					System.out.println("RECCO:" + recco.getName());
					request.setAttribute("recco",recco);
					
				} catch (Exception e) {
					System.out.println("Error in Filter: " + e.getLocalizedMessage());
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}