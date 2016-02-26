package adhoc;

import java.io.IOException;
import adhoc.MyResponse;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet Filter implementation class FinePrint
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.INCLUDE }
					, 
		urlPatterns = { 
				"/FinePrint", 
				"/Result.jspx"
		})
public class FinePrint implements Filter {

    /**
     * Default constructor. 
     */
	//StringWriter sw;
	
	
    public FinePrint() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public PrintWriter getWriter(HttpServletResponse res){
		StringWriter sw = new StringWriter();
		
		return new PrintWriter(sw);
	}
	
	public String getContent(HttpServletResponse res){
		StringWriter sw = new StringWriter();
		
		return sw.toString();
	}
	
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse res = (HttpServletResponse) response;
		res = new MyResponse(res);
		
		chain.doFilter(request, res);
		String old = ((MyResponse)res).getContent();
		
		
		/*
		String old = this.getContent(res);
		old.replaceAll("payment is:", "payment is:*");
		old.replaceAll("</form>", "<hr/>*Limitted time offer.</form>");
		response.getWriter().print(old);
		*/
		//HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(res);
		
		
		System.out.println("OLD CONTENT: " + old);
		if(((String)request.getAttribute("bank")).contentEquals("CIBC")){
		//OutputStream os = response.getOutputStream();
		old = old.replaceAll("payment is:", "payment is*:");
		old = old.replaceAll("</form>", "<hr/>*Limitted time offer.</form>");
		}
		
		response.getWriter().print(old);
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	



}




