package adhoc;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyResponse extends HttpServletResponseWrapper {
	
       StringWriter sw;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyResponse(HttpServletResponse response) {
        super(response);
        this.sw = new StringWriter();
        
        // TODO Auto-generated constructor stub
    }
    
    public PrintWriter getWriter(){
    	return new PrintWriter(this.sw);
    }
    
    public String getContent(){
    	//System.out.println("GET CONTENT " + this.sw.toString());
    	return this.sw.toString();
    }

	
}
