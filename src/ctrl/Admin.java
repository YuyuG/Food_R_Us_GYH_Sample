package ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet({"/Admin", "/Start/Admin"})

public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = "";
		String[] pwdFile = new String[2];
		String fullpath = request.getServletContext().getRealPath("/WEB-INF/passwords");
		//System.out.println(fullpath);
		
		if(request.getParameter("doit") == null){
			
			System.out.println("Login page");
			
			request.setAttribute("target", "Login.jspx");
			
			
		}else{
			
			
			request.setAttribute("target", "MaxP.jspx");
			System.out.println("GET IT TO WORK");
			if(request.getParameter("user") != null){
				username = (String)request.getParameter("user");
				
				System.out.println("GET IT TO WORK!!!" + username);
		        // This will reference one line at a time
		        String line = null;
		        
		        try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = 
		                new FileReader(fullpath);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = 
		                new BufferedReader(fileReader);
		            int count = 0;
		            while((line = bufferedReader.readLine()) != null) {
		               pwdFile[count] = line;
		               count += 1;
		               System.out.println("Has next line ");
		            }   

		            // Always close files.
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(
		                "Unable to open file '" + 
		                		fullpath + "'");                
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error reading file '" 
		                + fullpath + "'");                  
		            // Or we could just do this: 
		            // ex.printStackTrace();
		        }
		        

				Map<String, String> pwdMap = new TreeMap<String, String>();
				
				for(int i = 0; i < pwdFile.length; i ++){
					
					String[] split = pwdFile[i].split(" ");
					pwdMap.put(split[0], split[1]);
					System.out.println(split[0]+" " +split[1]);
				}
				
				String userInput = (String) request.getParameter("pwd");
				String hash = "";
				try{
				 hash = javax.xml.bind.DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA1").digest((userInput).getBytes()));
				 System.out.println(hash);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				boolean check = false;
				for(Map.Entry<String, String> entry : pwdMap.entrySet()){
					
					if(entry.getKey().contentEquals(username) && entry.getValue().contentEquals(hash)){
						check = true;
						break;
						
					}else{
						check = false;
					}
				}
				
				if(check){
				request.getSession().setAttribute("loggedIn", username);
				}
				
				
				
				
				if(!check){
					
					if(request.getSession().getAttribute("loggedIn") != null){
						request.getSession().removeAttribute("loggedIn");
						
					}
					request.setAttribute("errorMsg", "Invalid Credentials!");
					
				}
				
				if(request.getSession().getAttribute("loggedIn") != null){
					request.setAttribute("target", "MaxP.jspx");
				}else{
					request.setAttribute("target", "Login.jspx");
				}
				
				
				  
		    }    
		        
		}	
		 
        this.getServletContext().getRequestDispatcher("/" + "Dashboard.jspx").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
       doGet(request, response);
        
	}

}
